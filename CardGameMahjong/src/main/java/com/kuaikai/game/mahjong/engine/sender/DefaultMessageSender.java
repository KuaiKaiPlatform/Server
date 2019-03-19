package com.kuaikai.game.mahjong.engine.sender;

import java.util.ArrayList;
import java.util.List;

import com.kuaikai.game.common.msg.CommonMsgHandler;
import com.kuaikai.game.common.play.GameDesk;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.common.play.MessageSender;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.msg.DefaultMsgCreator;
import com.kuaikai.game.mahjong.msg.MsgCreator;
import com.kuaikai.game.mahjong.msg.MsgId;
import com.kuaikai.game.mahjong.msg.pb.SCanOperPB.SCanOper;
import com.kuaikai.game.mahjong.msg.pb.SGameResultPB.SGameResult;
import com.kuaikai.game.mahjong.msg.pb.SOperCardPB.SOperCard;
import com.kuaikai.game.mahjong.msg.pb.SSetInitPB.SSetInit;
import com.kuaikai.game.mahjong.msg.pb.SSetResultPB.SSetResult;

public class DefaultMessageSender extends MessageSender {
	
	protected MsgCreator msgCreator;
	
	public DefaultMessageSender(GameDesk desk) {
		super(desk);
		msgCreator = new DefaultMsgCreator();
	}
	
	private MahjongDesk getDesk() {
		return (MahjongDesk)desk;
	}
	
	public void setMsgCreator(MsgCreator msgCreator) {
		this.msgCreator = msgCreator;
	}

	/**
	 * 同步牌桌信息
	 */
	public void sendSSetInit(GamePlayer p) {
		List<GamePlayer> receivers = new ArrayList<GamePlayer>();
		if(p == null)
			receivers.addAll(desk.getAllPlayers());
		else
			receivers.add(p);
		
		for(GamePlayer receiver : receivers) {
			SSetInit.Builder builder = msgCreator.createSSetInit(this.getDesk(), (MahjongPlayer)receiver);
			OnlineManager.sendMsg(receiver.getId(), new CommonMsgHandler(MsgId.SSetInit, builder.build()));
		}
		
	}
	
	/**
	 * 同步玩家最近的操作和可执行的操作
	 */
	public void sendSOperCard() {
		for(GamePlayer receiver : desk.getAllPlayers()) {
			SOperCard.Builder builder = msgCreator.createSOperCard(this.getDesk(), (MahjongPlayer)receiver);
			OnlineManager.sendMsg(receiver.getId(), new CommonMsgHandler(MsgId.SOperCard, builder.build()));
		}
	}
	
	/**
	 * 同步指定操作，仅用于一炮多响时发送胡牌消息
	 */
	public void sendSOperCard(BaseOperation oper) {
		SOperCard.Builder builder = msgCreator.createSOperCard(oper);
		OnlineManager.sendToAll(desk.getPids(), new CommonMsgHandler(MsgId.SOperCard, builder.build()));
	}
	
	/**
	 * 过牌后如果有更多可执行的操作，同步给操作者
	 */
	public void sendSCanOper() {
		List<BaseOperation> canExecuteOperations = getDesk().getEngine().getOperManager().getCurrentCanExecuteOperations();
		if(canExecuteOperations == null || canExecuteOperations.isEmpty()) return;
		
		for(GamePlayer receiver : desk.getAllPlayers()) {
			SCanOper.Builder builder = msgCreator.createSCanOper(this.getDesk(), (MahjongPlayer)receiver, canExecuteOperations);
			if(builder.getCanOperDetailsCount() == 0) continue;
			OnlineManager.sendMsg(receiver.getId(), new CommonMsgHandler(MsgId.SCanOper, builder.build()));
		}
	}

	/**
	 * 发送本局结束消息
	 */
	public void sendSSetResult(boolean over) {
		SSetResult.Builder builder = msgCreator.createSSetResult(this.getDesk(), over);
		OnlineManager.sendToAll(desk.getPids(), new CommonMsgHandler(MsgId.SSetResult, builder.build()));
	}
	
	/**
	 * 发送整场结束消息
	 */
	public void sendSGameResult(boolean dismiss) {
		SGameResult.Builder builder = msgCreator.createSGameResult(this.getDesk(), dismiss);
		OnlineManager.sendToAll(desk.getPids(), new CommonMsgHandler(MsgId.SGameResult, builder.build()));
	}
	
}
