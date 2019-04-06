package com.kuaikai.game.logic.desk;

import java.util.List;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.conf.PropertyManager;
import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.Club;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.CommonMsgHandler;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GameDesk;
import com.kuaikai.game.common.play.GameDeskManager;
import com.kuaikai.game.common.redis.ClubDeskRedis;
import com.kuaikai.game.common.redis.ClubRedis;
import com.kuaikai.game.common.redis.ClubRuleRedis;
import com.kuaikai.game.common.redis.LockRedis;
import com.kuaikai.game.common.redis.PlayerArenaRedis;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.hall.msg.DefaultMsgCreator;
import com.kuaikai.game.hall.msg.MsgCreator;
import com.kuaikai.game.hall.msg.MsgId;
import com.kuaikai.game.logic.play.GameDeskFactory;
import com.kuaikai.game.logic.play.GameManager;

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
		
		// 已入座
		if(isSeated(clubId, deskId, uid)) {
			logger.debug("DeskManager.onUserLogin@seated|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
			return;
		}
		
		// 无竞技场
		if(clubId == 0) {
			if(PropertyManager.isDebug()) {
				// DEBUG 模式时，设置为大众陕西麻将竞技场，大众竞技场 clubId 等于 game rule 
				//clubId = ClubMock.CLUB_ID_PUB_LIANG;
				clubId = GameRule.GUO_ZI_VALUE;
				//clubId = GameRule.SXMJ_VALUE;
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
		
		// 大众竞技场：加入当前牌桌，发送 SDeskInfo，给牌桌其他玩家发送 SPlayerJoin。若满足开局条件，启动开局过程
		deskId = joinPubDesk(clubId, uid);
		
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

	/*
	 * 	加入大众竞技场牌桌：
	  *    大众竞技场，坐满后自动开局，等待和开始之间无坐满状态
	 * 
	 * @param clubId
	 * @param uid
	 * @return
	 */
	private static long joinPubDesk(int clubId, int uid) {
		long deskId = 0;
		// 锁定竞技场
		RLock rLockClub = LockRedis.getClubLock(clubId);
		rLockClub.lock();
		
		try {
			// 当前牌桌不存在或不是等待状态，增加当前牌桌号
			deskId = ClubRedis.getDeskId(clubId);
			if(deskId <= 0 || ClubDeskRedis.getStatus(clubId, deskId) != GameStatus.WAITING_VALUE) {
				deskId = ClubRedis.incrDeskId(clubId);
				
				// 设置牌桌玩法
				List<GameRule> rules = ClubRuleRedis.getGameRules(clubId);
				GameRule rule = rules.get(0);
				ClubDeskRedis.putRule(clubId, deskId, rule);
			}
			
			// 玩法规则是否有下注操作
			AttrsModel setting = ClubDeskRedis.getSetting(clubId, deskId, null);
			boolean xiaZhu = setting.getBool(CardGameSetting.XIA_ZHU);
			
			// 入座
			int seat = ClubDeskRedis.putNextSeat(clubId, deskId, uid);
			ClubDeskRedis.playerTakeSeat(clubId, deskId, uid, seat, !xiaZhu);	// 无下注操作，入座后直接准备
			PlayerArenaRedis.putDeskId(uid, deskId);
			logger.info("com.kuaikai.game.logic.desk.DeskManager.joinPubDesk@seated|uid={}|clubId={}|deskId={}|seat={}", uid, clubId, deskId, seat);
			
			GameDesk gameDesk = GameDeskManager.get(Desk.getKey(clubId, deskId));
			if(gameDesk == null) {
				// 新建游戏牌桌
				Desk desk = ClubDeskRedis.getDesk(clubId, deskId);
				gameDesk = GameDeskFactory.create(desk);
				if(gameDesk == null) {
					logger.error("com.kuaikai.game.logic.desk.DeskManager.joinPubDesk@Unsupported game rule|desk={}", desk);
					return deskId;
				}
				gameDesk.initBanker();
				GameDeskManager.add(gameDesk);
			} else {
				gameDesk.addPlayer(ClubDeskRedis.getPlayer(clubId, deskId, uid));
			}
			
			// 牌桌坐满
			int totalPlayer = setting.getInt(CardGameSetting.TOTAL_PLAYER);
			if(Desk.isFull(totalPlayer, seat)) {
				ClubDeskRedis.putStatus(clubId, deskId, GameStatus.FULL_VALUE);
				gameDesk.setStatus(GameStatus.FULL);
			}
			
			// 发送 SDeskInfo 和 SPlayerJoin
			Desk desk = gameDesk.getDesk();
			OnlineManager.sendMsg(uid, new CommonMsgHandler(MsgId.SDeskInfo, msgCreator.createSDeskInfo(desk).build()));
			OnlineManager.sendToAll(ClubDeskRedis.getPlayerIds(clubId, deskId), new CommonMsgHandler(MsgId.SPlayerJoin,
					msgCreator.createSPlayerJoin(desk.getPlayerById(uid), desk).build()), uid);
			
			// 无下注操作，牌桌坐满后开始游戏
			if(gameDesk.checkStatus(GameStatus.FULL) && !xiaZhu) {
				GameManager.startAsync(gameDesk);
			}
		} catch(Exception e) {
			logger.error("com.kuaikai.game.logic.desk.DeskManager.joinPubDesk@error|uid={}|clubId={}|deskId={}", uid, clubId, deskId, e);
		} finally {
			rLockClub.unlock();
		}
		return deskId;
	}
	
}
