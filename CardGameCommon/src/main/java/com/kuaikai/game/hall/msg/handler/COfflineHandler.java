package com.kuaikai.game.hall.msg.handler;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.CommonMsgHandler;
import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.msg.pb.DeskUniqPB.DeskUniq;
import com.kuaikai.game.common.play.GameDesk;
import com.kuaikai.game.common.play.GameDeskManager;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.common.redis.ClubDeskRedis;
import com.kuaikai.game.common.redis.LockRedis;
import com.kuaikai.game.common.redis.PlayerArenaRedis;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.hall.msg.MsgId;
import com.kuaikai.game.hall.msg.pb.COfflinePB.COffline;
import com.kuaikai.game.hall.msg.pb.SOfflinePB.SOffline;

import io.netty.channel.ChannelHandlerContext;

public class COfflineHandler extends MsgHandler {

	private static final Logger logger = LoggerFactory.getLogger("hall");

	public COfflineHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public static final int msgid = MsgId.COffline;
	private COffline msg;

	@Override
	public void process() {
		int uid = getUid();
		
		// 找到牌桌
		int clubId = PlayerArenaRedis.getClubId(uid);
		long deskId = PlayerArenaRedis.getDeskId(uid);
		
		if(clubId <= 0 || deskId <= 0) {
			logger.error("COfflineHandler.process@invalid club or desk|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
			return;
		}
		
		// 获取 club desk lock
		RLock rLock = LockRedis.getClubDeskLock(clubId, deskId);
		rLock.lock();
		try {
			// 获取牌桌
			GameDesk desk = GameDeskManager.get(Desk.getKey(clubId, deskId));
			if(desk == null) {
				logger.error("COfflineHandler.process@Game desk not found|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			// 玩家未入座
			GamePlayer player = desk.getPlayerById(uid);
			if(player == null) {
				logger.error("COfflineHandler.process@player not found|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			boolean offline = msg.getOffline();
			
			// 修改 Redis
			ClubDeskRedis.playerOffline(clubId, deskId, uid, offline);
			
			// 修改准备状态
			player.setOffline(offline);
			
			logger.info("COfflineHandler.process@uid={}|clubId={}|deskId={}|offline={}", uid, clubId, deskId, offline);
			
			// 发送 SOffline 
			SOffline sOffline = SOffline.newBuilder()
					.setUid(uid)
					.setOffline(offline)
					.setUniq(DeskUniq.newBuilder().setClubId(clubId).setDeskId(deskId))
					.build();
			OnlineManager.sendToAll(desk.getPids(), new CommonMsgHandler(MsgId.SOffline, sOffline));

		} catch (Exception e) {
			logger.error("COfflineHandler.process@error|uid={}|clubId={}|deskId={}", uid, clubId, deskId, e);
		} finally {
			rLock.unlock();
		}

	}

	@Override
	public void decode(Message message) throws Exception {
		// 数据解析
		msg = COffline.parseFrom(message.bytes);
	}

	@Override
	public Message encode() throws Exception {
		return null;
	}

	@Override
	public String desc() {
		return String.format("msgid=%d|data=%s", msgid, msg);
	}

}
