package com.kuaikai.game.mahjong.msg.handler;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.CommonMsgHandler;
import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GameDeskManager;
import com.kuaikai.game.common.redis.LockRedis;
import com.kuaikai.game.common.redis.PlayerArenaRedis;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.msg.MsgId;
import com.kuaikai.game.mahjong.msg.pb.CReadyPB.CReady;
import com.kuaikai.game.mahjong.msg.pb.SReadyPB.SReady;

import io.netty.channel.ChannelHandlerContext;

public class CReadyHandler extends MsgHandler {

	private static final Logger logger = LoggerFactory.getLogger("mahjong");

	public CReadyHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public static final int msgid = MsgId.CReady;
	private CReady cReady;

	@Override
	public void process() {
		int uid = getUid();
		
		// 找到牌桌
		int clubId = PlayerArenaRedis.getClubId(uid);
		long deskId = PlayerArenaRedis.getDeskId(uid);
		
		if(clubId <= 0 || deskId <= 0) {
			logger.error("CReadyHandler.process@invalid club or desk|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
			return;
		}
		
		// 获取 club desk lock
		RLock rLock = LockRedis.getClubDeskLock(clubId, deskId);
		rLock.lock();
		try {
			// 获取麻将牌桌
			MahjongDesk desk = (MahjongDesk)GameDeskManager.get(Desk.getKey(clubId, deskId));
			
			if(desk == null) {
				logger.error("CReadyHandler.process@desk not found|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			// 麻将从第二局开始需要发送准备
			if(!desk.checkStatus(GameStatus.SET_ENDING)) {
				logger.error("CReadyHandler.process@game status error|uid={}|clubId={}|deskId={}|status={}", uid, clubId, deskId, desk.getStatus());
				return;
			}
			
			// 玩法规则有下注，应发送 CBet
			if(desk.getSetting().getBool(CardGameSetting.XIA_ZHU)) {
				logger.error("CReadyHandler.process@setting has xia zhu|uid={}|clubId={}|deskId={}|rule={}", uid, clubId, deskId, desk.getRule());
				return;
			}
			
			MahjongPlayer player = desk.getPlayerById(uid);
			if(player == null) {
				logger.error("CReadyHandler.process@player not found|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			// 准备
			player.onReady();
			logger.info("CReadyHandler.process@uid={}|clubId={}|deskId={}", uid, clubId, deskId);
			
			// 发送 SReady
			OnlineManager.sendToAll(desk.getPids(), new CommonMsgHandler(MsgId.SReady, SReady.newBuilder().setUid(uid).build()));
			
			// 检查是否都准备好，开局发送 SSetInit
			desk.checkSetStart();
			
		} catch (Exception e) {
			logger.error("CReadyHandler.process@error|uid={}|clubId={}|deskId={}", uid, clubId, deskId, e);
		} finally {
			rLock.unlock();
		}

	}

	@Override
	public void decode(Message message) throws Exception {
		// 无数据，不需要解析
		//cPassCard = CPassCard.parseFrom(message.bytes);
	}

	@Override
	public Message encode() throws Exception {
		return null;
	}

	@Override
	public String desc() {
		return String.format("msgid=%d|data=%s", msgid, cReady);
	}

}
