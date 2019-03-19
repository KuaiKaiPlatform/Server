package com.kuaikai.game.mahjong.msg.handler;

import java.util.List;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.play.GameDeskManager;
import com.kuaikai.game.common.redis.LockRedis;
import com.kuaikai.game.common.redis.PlayerArenaRedis;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.msg.MsgId;
import com.kuaikai.game.mahjong.msg.pb.CPassCardPB.CPassCard;

import io.netty.channel.ChannelHandlerContext;

public class CPassCardHandler extends MsgHandler {

	private static final Logger logger = LoggerFactory.getLogger("mahjong");

	public CPassCardHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public static final int msgid = MsgId.CPassCard;
	private CPassCard cPassCard;

	@Override
	public void process() {
		int uid = getUid();
		
		// 找到牌桌
		int clubId = PlayerArenaRedis.getClubId(uid);
		long deskId = PlayerArenaRedis.getDeskId(uid);
		
		if(clubId <= 0 || deskId <= 0) {
			logger.error("CPassCardHandler.process@invalid club or desk|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
			return;
		}
		
		logger.info("CPassCardHandler.process@uid={}|clubId={}|deskId={}", uid, clubId, deskId);
		
		// 获取 club desk lock
		RLock rLock = LockRedis.getClubDeskLock(clubId, deskId);
		rLock.lock();
		try {
			// 获取麻将牌桌
			MahjongDesk desk = (MahjongDesk)GameDeskManager.get(Desk.getKey(clubId, deskId));
			
			if(desk == null) {
				logger.error("CPassCardHandler.process@desk not found|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			if(!desk.checkStatus(GameStatus.STARTING)) {
				logger.error("CPassCardHandler.process@game status error|uid={}|clubId={}|deskId={}|status={}", uid, clubId, deskId, desk.getStatus());
				return;
			}
			
			if(!desk.getEngine().isStageGaming()) {
				logger.error("CPassCardHandler.process@stage error|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			MahjongPlayer player = desk.getPlayerById(uid);
			if(player == null) {
				logger.error("CPassCardHandler.process@player not found|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			// 找到可过牌的操作
			List<BaseOperation> operations = desk.getEngine().getOperManager().findCurrentCanExecuteOperations(player);
			if (operations == null || operations.isEmpty()) {
				logger.error("CPassCardHandler.process@operation not found|uid={}|clubId={}|deskId={}", uid, clubId, deskId);
				return;
			}
			
			// 一炮多响
			if(desk.getEngine().getOperManager().isMultipleHu()) {
				desk.getEngine().getOperManager().getMultipleHuOperations().executePass(player);
				return;
			}
			
			// 记下过牌操作
			for(BaseOperation oper : operations) {
				oper.setPassed();
				logger.info("CPassCardHandler.process@pass card|uid={}|clubId={}|deskId={}|operType={}|target={}", uid, clubId, deskId, oper.getOperType(), oper.getTarget());
			}
			
			// 执行过牌
			desk.getEngine().executePass(player);
			
			// 检查是否进入结算阶段，本局结束
			desk.getEngine().checkJieSuanStage();
			
		} catch (Exception e) {
			logger.error("CPassCardHandler.process@error|uid={}|clubId={}|deskId={}", uid, clubId, deskId, e);
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
		return String.format("msgid=%d|data=%s", msgid, cPassCard);
	}

}
