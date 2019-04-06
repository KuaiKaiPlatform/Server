package com.kuaikai.game.mahjong.msg.handler;

import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.Message;
import com.kuaikai.game.common.msg.MsgHandler;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GameDeskManager;
import com.kuaikai.game.common.redis.LockRedis;
import com.kuaikai.game.common.redis.PlayerArenaRedis;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.OperDetail;
import com.kuaikai.game.mahjong.msg.MsgId;
import com.kuaikai.game.mahjong.msg.pb.COperCardPB.COperCard;
import com.kuaikai.game.mahjong.msg.pb.OperDetailPB;

import io.netty.channel.ChannelHandlerContext;

public class COperCardHandler extends MsgHandler {

	private static final Logger logger = LoggerFactory.getLogger("mahjong");

	public COperCardHandler(ChannelHandlerContext ctx) {
		super(ctx);
	}

	public COperCardHandler(int uid, COperCard cOperCard) {
		super(null);
		this.cOperCard = cOperCard;
		this.uid = uid;
	}
	
	public static final int msgid = MsgId.COperCard;
	private COperCard cOperCard;
	private int uid;

	@Override
	public void process() {
		uid = uid>0?uid:getUid();
		
		// 找到牌桌
		int clubId = PlayerArenaRedis.getClubId(uid);
		long deskId = PlayerArenaRedis.getDeskId(uid);
		OperDetailPB.OperDetail operDetail = cOperCard.getOperDetail();
		
		if(clubId <= 0 || deskId <= 0) {
			logger.error("COperCardHandler.process@invalid club or desk|uid={}|clubId={}|deskId={}|operDetails={}", uid, clubId, deskId, operDetail);
			return;
		}
		
		logger.info("COperCardHandler.process@uid={}|clubId={}|deskId={}|operDetails={}", uid, clubId, deskId, operDetail);
		
		// 获取 club desk lock
		RLock rLock = LockRedis.getClubDeskLock(clubId, deskId);
		rLock.lock();
		try {
			// 获取麻将牌桌
			MahjongDesk desk = (MahjongDesk)GameDeskManager.get(Desk.getKey(clubId, deskId));
			
			if(desk == null) {
				logger.error("COperCardHandler.process@desk not found|uid={}|clubId={}|deskId={}|operDetails={}", uid, clubId, deskId, operDetail);
				return;
			}
			
			if(!desk.checkStatus(GameStatus.STARTING)) {
				logger.error("COperCardHandler.process@game status error|uid={}|clubId={}|deskId={}|status={}|operDetails={}", uid, clubId, deskId, desk.getStatus(), operDetail);
				return;
			}
			
			if(!desk.getEngine().isStageGaming()) {
				logger.error("COperCardHandler.process@stage error|uid={}|clubId={}|deskId={}|operDetails={}", uid, clubId, deskId, operDetail);
				return;
			}
			
			// 一炮多响
			if(desk.getEngine().getOperManager().isMultipleHu()) {
				MahjongPlayer player = desk.getPlayerById(uid);
				desk.getEngine().getOperManager().getMultipleHuOperations().executeHu(player);
				return;
			}
			
			// 找到可执行的操作
			OperDetail od = new OperDetail();
			od.setUid(uid);
			od.setOperType(operDetail.getOperType().getNumber());
			od.setTarget(operDetail.getTarget());
			od.addCards(operDetail.getCardsList());
			BaseOperation oper = desk.getEngine().getOperManager().findCurrentCanExecuteOperation(od);
			if (oper == null) {
				logger.error("COperCardHandler.process@operation not found|uid={}|clubId={}|deskId={}|operDetails={}", uid, clubId, deskId, operDetail);
				return;
			}
			
			// 执行本操作
			oper.execute();
			
			// 发送操作结果
			desk.getMessageSender().sendSOperCard();
			
			// 操作完成
			desk.getEngine().onOperationEnd(oper);
			
			// 检查是否进入结算阶段，本局结束
			boolean jieSuan = desk.getEngine().checkJieSuanStage();
			
			// 牌局未结束，自动出牌秒数有设置，启动定时任务，自动出牌
			if(!jieSuan) {
				desk.getEngine().getOperManager().scheduleOperation();
			}
			
		} catch (Exception e) {
			logger.error("COperCardHandler.process@error|uid={}|clubId={}|deskId={}|operDetails={}", uid, clubId, deskId, operDetail, e);
		} finally {
			rLock.unlock();
		}

	}

	@Override
	public void decode(Message message) throws Exception {
		cOperCard = COperCard.parseFrom(message.bytes);
	}

	@Override
	public Message encode() throws Exception {
		return null;
	}

	@Override
	public String desc() {
		return String.format("msgid=%d|data=%s", msgid, cOperCard);
	}

}
