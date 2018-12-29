package com.kuaikai.game.logic.desk;

import java.util.List;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.conf.PropertyManager;
import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.desk.DeskStartEvent;
import com.kuaikai.game.common.mock.ClubMock;
import com.kuaikai.game.common.model.Club;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.CommonMsgHandler;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.redis.ClubDeskRedis;
import com.kuaikai.game.common.redis.ClubRedis;
import com.kuaikai.game.common.redis.ClubRuleRedis;
import com.kuaikai.game.common.redis.LockRedis;
import com.kuaikai.game.common.redis.PlayerArenaRedis;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.common.utils.CollectionUtils;
import com.kuaikai.game.hall.msg.DefaultMsgCreator;
import com.kuaikai.game.hall.msg.MsgCreator;
import com.kuaikai.game.hall.msg.MsgId;

public class DeskManager {
	
	private static final Logger logger = LoggerFactory.getLogger(DeskManager.class);
	
	private static MsgCreator msgCreator;
	
	public static void init() {
		msgCreator = new DefaultMsgCreator();
	}
	
	/**
	 * 登录后加入牌桌：
	 * 1. 玩家已入座，发送 SDeskInfo
	 * 2. 玩家未入座
	 *  2.1 无竞技场：debug模式时，加入测试用大众竞技场当前牌桌，发送 SDeskInfo，给牌桌其他玩家发送 SPlayerJoin；非debug模式，记录错误，返回。
	 * 	2.2 私有竞技场：记录错误，返回。
	 * 	2.3 大众竞技场：加入当前牌桌，发送 SDeskInfo，给牌桌其他玩家发送 SPlayerJoin
	 * 
	 * @param uid
	 */
	public static void onUserLogin(int uid) {
		// 登录后执行准备阶段：生成牌桌，入座等
		//PrepareManager.onUserLogin(uid);
		
		// 找到牌桌
		int clubId = PlayerArenaRedis.getClubId(uid);
		long deskId = PlayerArenaRedis.getDeskId(uid);
		
		logger.debug("DeskManager.onUserLogin@uid={}|clubId={}|deskId={}", uid, clubId, deskId);
		
		// 是否已入座
		if(isSeated(clubId, deskId, uid)) {
			logger.debug("DeskManager.onUserLogin@seated|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
			return;
		}
		
		// 无竞技场
		if(clubId == 0) {
			if(PropertyManager.isDebug()) {
				// DEBUG 模式时，设置为大众亮六飞一竞技场
				clubId = ClubMock.CLUB_ID_PUB_LIANG;
				PlayerArenaRedis.putClubId(uid, clubId);
				logger.info("DeskManager.onUserLogin@Club set as {} for debug|uid={}", clubId, uid);
			} else {
				// 非debug模式，记录错误，返回。
				logger.warn("DeskManager.onUserLogin@Club not found|uid={}", uid);
				return;	
			}
		}
		
		// 私有竞技场，未入座，记录错误，返回。
		if(!Club.isPub(ClubRedis.getOwnerId(clubId))) {
			logger.warn("DeskManager.onUserLogin@Private club desk does not exist|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
			return;
		}
		
		// 大众竞技场：加入当前牌桌，发送 SDeskInfo，给牌桌其他玩家发送 SPlayerJoin
		deskId = joinDesk(clubId, uid);
		
		
		RLock rLockDesk = LockRedis.getClubDeskLock(clubId, deskId);
		rLockDesk.lock();
		
		try {
			
			
			
			Desk desk = ClubDeskRedis.getDesk(clubId, deskId);
			OnlineManager.sendMsg(uid, new CommonMsgHandler(MsgId.SDeskInfo, msgCreator.createSDeskInfo(desk).build()));
			if(desk.canStart()) {
				String deskKey = desk.getKey();
				logger.debug("DeskManager.onUserLogin@can start desk={}", deskKey);
				try {
					// 通知牌局开始
					DeskStartEvent startEvent = new DeskStartEvent(deskKey);
					TriggerManager.triggerEvent(startEvent);
				} catch (Exception e) {
					logger.error("DeskManager.onUserLogin@desk start listener error|desk={}", desk, e);
				} finally {
					//rLock.unlock();
				}
			}
		} catch(Exception e) {
			logger.error("DeskManager.onUserLogin@error|uid={}|clubId={}|deskId={}", uid, clubId, deskId, e);
		} finally {
			rLockDesk.unlock();
		}
	}
	
	private static boolean isSeated(int clubId, long deskId, int uid) {
		if(clubId <= 0 || deskId <= 0) return false;
		RLock rLockDesk = LockRedis.getClubDeskLock(clubId, deskId);
		rLockDesk.lock();
		
		boolean seated = false;
		try {
			seated = ClubDeskRedis.isPlayerSeated(clubId, deskId, uid);
			if(seated) sendSDeskInfo(clubId, deskId, uid);
		} catch(Exception e) {
			logger.error("DeskManager.isSeated@error|uid={}|clubId={}|deskId={}", uid, clubId, deskId, e);
		} finally {
			rLockDesk.unlock();
		}
		return seated;
	}
	
	private static void sendSDeskInfo(int clubId, long deskId, int uid) {
		Desk desk = ClubDeskRedis.getDesk(clubId, deskId);
		OnlineManager.sendMsg(uid, new CommonMsgHandler(MsgId.SDeskInfo, msgCreator.createSDeskInfo(desk).build()));
	}

	private static long joinDesk(int clubId, int uid) {
		long deskId = 0;
		// 锁定竞技场
		RLock rLockClub = LockRedis.getClubLock(clubId);
		rLockClub.lock();
		
		try {
			// 当前牌桌不存在或不是等待状态，增加当前牌桌号
			deskId = ClubRedis.getDeskId(clubId);
			if(deskId <= 0 || CollectionUtils.getMapInt(ClubDeskRedis.getRMap(clubId, deskId), ClubDeskRedis.FIELD_STATUS) != GameStatus.WAITING_VALUE) {
				deskId = ClubRedis.incrDeskId(clubId);
			}
			// 入座，牌桌坐满后修改牌桌游戏状态为开始
			int seat = ClubDeskRedis.putNextSeat(clubId, deskId, uid);
			ClubDeskRedis.playerSeat(clubId, deskId, uid, seat);
			
			logger.info("DeskManager.join@seated|uid={}|clubId={}|deskId={}|seat={}", uid, clubId, deskId, seat);
			
			List<GameRule> rules = ClubRuleRedis.getGameRules(clubId);
			GameRule rule = rules.get(0);
			int totalPlayer = CollectionUtils.getMapInt(ClubRuleRedis.getRMapSetting(clubId, rule), CardGameSetting.TOTAL_PLAYER);
			if(Desk.isFull(totalPlayer, seat)) {
				ClubDeskRedis.putAttr(clubId, deskId, ClubDeskRedis.FIELD_STATUS, String.valueOf(GameStatus.STARTING_VALUE));
			}
		} catch(Exception e) {
			logger.error("DeskManager.join@error|uid={}|clubId={}|deskId={}", uid, clubId, deskId, e);
		} finally {
			rLockClub.unlock();
		}
		return deskId;
	}
	
}
