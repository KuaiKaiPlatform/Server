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
import com.kuaikai.game.common.redis.ClubDeskRedis;
import com.kuaikai.game.common.redis.LockRedis;
import com.kuaikai.game.common.redis.PlayerArenaRedis;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.msg.MsgId;
import com.kuaikai.game.mahjong.msg.pb.CBetPB.CBet;
import com.kuaikai.game.mahjong.msg.pb.SBetPB.SBet;

import io.netty.channel.ChannelHandlerContext;

public class CBetHandler extends MsgHandler {

	private static final Logger logger = LoggerFactory.getLogger("mahjong");

	public CBetHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public static final int msgid = MsgId.CBet;
	private CBet cBet;

	@Override
	public void process() {
		int uid = getUid();
		
		// 找到牌桌
		int clubId = PlayerArenaRedis.getClubId(uid);
		long deskId = PlayerArenaRedis.getDeskId(uid);
		
		if(clubId <= 0 || deskId <= 0) {
			logger.error("CBetHandler.process@invalid club or desk|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
			return;
		}
		
		// 获取 club desk lock
		RLock rLock = LockRedis.getClubDeskLock(clubId, deskId);
		rLock.lock();
		try {
			// 获取麻将牌桌
			MahjongDesk desk = (MahjongDesk)GameDeskManager.get(Desk.getKey(clubId, deskId));
			if(desk == null) {
				logger.error("CBetHandler.process@mahjong desk not found|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			// 牌局未开始或一局结束后开始下注
			if(desk.checkStatus(GameStatus.STARTING) || desk.checkStatus(GameStatus.ENDING)) {
				logger.error("CBetHandler.process@game status error|uid={}|clubId={}|deskId={}|status={}", uid, clubId, deskId, desk.getStatus());
				return;
			}
			
			// 是否下注阶段
//			if(!desk.getEngine().isStageXiaZhu()) {
//				logger.error("CBetHandler.process@game stage error|uid={}|clubId={}|deskId={}|rule={}|stage={}", uid, clubId, deskId, desk.getRule(), desk.getEngine().getStage());
//				return;
//			}
			
			// 玩家未入座
			MahjongPlayer player = desk.getPlayerById(uid);
			if(player == null) {
				logger.error("CBetHandler.process@player not found|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			// 玩法规则是否有下注
			if(!desk.getSetting().getBool(CardGameSetting.XIA_ZHU)) {
				logger.error("CBetHandler.process@setting without xia zhu|uid={}|clubId={}|deskId={}|rule={}", uid, clubId, deskId, desk.getRule());
				return;
			}

			// 下注数是否有效
			int bet = cBet.getBet();
			if(bet < 0) {
				logger.error("CBetHandler.process@invalid bet|uid={}|clubId={}|deskId={}|rule={}|bet={}", uid, clubId, deskId, desk.getRule(), bet);
				return;
			}
			
			// 玩家已经下注
			if(player.isReady()) {
				logger.error("CBetHandler.process@player is ready|uid={}|clubId={}|deskId={}|rule={}|bet={}", uid, clubId, deskId, desk.getRule(), player.getBet());
				return;
			}
			
			// 修改 Redis
			ClubDeskRedis.playerBet(clubId, deskId, uid, bet);
			
			// 设置下注数
			player.setBet(bet);
			
			// 准备
			player.onReady();
			
			logger.info("CBetHandler.process@uid={}|clubId={}|deskId={}|bet={}", uid, clubId, deskId, bet);
			
			// 发送 SBet 和 SReady 
			OnlineManager.sendToAll(desk.getPids(), new CommonMsgHandler(MsgId.SBet, SBet.newBuilder().setUid(uid).setBet(bet).build()));
			//OnlineManager.sendToAll(desk.getPids(), new CommonMsgHandler(MsgId.SReady, SReady.newBuilder().setUid(uid).build()));
			
			// 检查是否都准备好，开局发送 SSetInit
			desk.checkSetStart();

		} catch (Exception e) {
			logger.error("CBetHandler.process@error|uid={}|clubId={}|deskId={}", uid, clubId, deskId, e);
		} finally {
			rLock.unlock();
		}

	}

	@Override
	public void decode(Message message) throws Exception {
		// 数据解析
		cBet = CBet.parseFrom(message.bytes);
	}

	@Override
	public Message encode() throws Exception {
		return null;
	}

	@Override
	public String desc() {
		return String.format("msgid=%d|data=%s", msgid, cBet);
	}

}
