package com.kuaikai.game.mahjong.engine.sender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.calculator.PlayerSetResult;
import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.OperDetail;
import com.sy.mahjong.core.RoomExtension;
import com.sy.mahjong.message.MessageContants;
import com.sy.mahjong.message.MessageHelper;

public class MessageSender {

	protected RoomExtension roomExt;
	protected MahjongDesk room;
	
	public MessageSender(RoomExtension roomExt) {
		this.roomExt = roomExt;
		this.room = roomExt.getRoom();
	}
	
	/**
	 * 同步牌桌信息
	 */
	public void syncSetStart(MahjongPlayer p) {
		List<MahjongPlayer> receivers = new ArrayList<MahjongPlayer>();
		if(p == null)
			receivers.addAll(room.getAllPlayers());
		else
			receivers.add(p);
		
		List<BaseOperation> canExecuteOperations = room.getEngine().getOperManager().getCurrentCanExecuteOperations();
		for(MahjongPlayer receiver : receivers) {
			SFSObject initSFSObject = getSetStartObj(receiver);
			// 可执行操作只发送给操作者
			initSFSObject.removeElement("canOperDetails");
			if(canExecuteOperations != null) {
				SFSArray canOperDetails = new SFSArray();
				for(BaseOperation canExecuteOperation : canExecuteOperations) {
					if(!canExecuteOperation.canExecute()) continue;
					if(!receiver.equals(canExecuteOperation.getPlayer())) continue;
					canOperDetails.addSFSObject(getOperDetail(canExecuteOperation, receiver).toSfsObject());
				}
				initSFSObject.putSFSArray("canOperDetails", canOperDetails);				
			}

			// 打牌后听的牌
			initSFSObject.removeElement("discard2TingCards");
			SFSObject tingCardsObj = getDiscard2TingCardsSFSObj(receiver);
			if(tingCardsObj != null) {
				initSFSObject.putSFSObject("discard2TingCards", tingCardsObj);
			}
			
			MessageHelper.sendMsg(roomExt, MessageContants.Init, initSFSObject, receiver);
			
			synUserCardInitInfo(receiver);
		}
		
	}
	
	/**
	 * 玩家牌桌信息
	 */
	protected SFSObject getGameInfo(MahjongPlayer player) {
		SFSObject sfsObject = new SFSObject();
		sfsObject.putInt("uid", player.getId());
		sfsObject.putInt("bet", player.getCurBet());
		sfsObject.putInt("queMen", player.getMjPlayer().getQueMenVal());
		sfsObject.putInt("handCardNum", player.getMjPlayer().getCardContainer().getHandCards().size());
		sfsObject.putIntArray("discards", player.getMjPlayer().getCardContainer().getDiscardValues());
		sfsObject.putSFSArray("cardGroups", player.getMjPlayer().getCardContainer().getCardGroupsSFS());
		return sfsObject;
	}

	/**
	 * 牌桌信息
	 */
	protected SFSObject getSetStartObj(MahjongPlayer receiver) {
		SFSObject initSFSObj = new SFSObject();
		SFSArray userGameInfos = new SFSArray();
		for (MahjongPlayer player : room.getAllPlayers()) {
			SFSObject userGameInfo = getGameInfo(player);
			userGameInfos.addSFSObject(userGameInfo);
		}
		initSFSObj.putSFSArray("useInfos", userGameInfos);
		
		initSFSObj.putInt("curSet", room.getCurSet());
		initSFSObj.putInt("remainCards", room.getEngine().getCardPool().remainCards());
		initSFSObj.putInt("bankerid", room.getBankerId());
		initSFSObj.putInt("stage", room.getEngine().getStage());
		
		BaseOperation lastDoneOperation = room.getEngine().getOperManager().getLastDoneOperation();
		if (lastDoneOperation != null) {
			initSFSObj.putSFSObject("lastOperDetail", getOperDetail(lastDoneOperation, receiver).toSfsObject());
		}
		
		return initSFSObj;
	}
	
	/**
	 * 同步玩家手牌信息
	 */
	protected void synUserCardInitInfo(MahjongPlayer player) {
		SFSObject cardInitInfo = new SFSObject();
		cardInitInfo.putIntArray("cards", player.getMjPlayer().getCardContainer().getHandCardValues());
		MessageHelper.sendMsg(roomExt, MessageContants.UserCardInitInfo, cardInitInfo, player);
	}
	
	protected OperDetail getOperDetail(BaseOperation oper, MahjongPlayer receiver) {
		return oper.toOperDetail(receiver);
	}
	
	/*
	 * 同步所有玩家定缺信息
	 */
	public void syncDingQueRes() {
		SFSObject dingQueRes = new SFSObject();
		SFSArray dingQues = new SFSArray();
		for (MahjongPlayer p : room.getAllPlayers()) {
			SFSObject dingQue = new SFSObject();
			dingQue.putInt("uid", p.getId());
			dingQue.putInt("queMen", p.getMjPlayer().getQueMenVal());
			dingQues.addSFSObject(dingQue);
		}
		dingQueRes.putSFSArray("dingQues", dingQues);
		MessageHelper.sendMsgToAll(roomExt, MessageContants.SynDingQueRes, dingQueRes, room.getAllPlayers());
	}
	
	/*
	 * 同步玩家最近的操作和可执行的操作
	 */
	public void syncOperCardRes() {
		for(MahjongPlayer receiver : room.getAllPlayers()) {
			syncOperCardRes(receiver);
		}
	}
	
	/*
	 * 同步玩家最近的操作和可执行的操作
	 */
	public void syncOperCardRes(MahjongPlayer receiver) {
		SFSObject sfsObject = new SFSObject();
		// 刚完成的操作
		List<BaseOperation> lastOperations = room.getEngine().getOperManager().getLastOperations();
		SFSArray operDetails = new SFSArray();
		for(BaseOperation oper : lastOperations) {
			operDetails.addSFSObject(getOperDetail(oper, receiver).toSfsObject());
		}
		sfsObject.putSFSArray("operDetails", operDetails);
		
		// 可执行操作
		List<BaseOperation> canExecuteOperations = room.getEngine().getOperManager().getCurrentCanExecuteOperations();
		if(canExecuteOperations != null && !canExecuteOperations.isEmpty()) {
			//sfsObject.removeElement("canOperDetails");
			SFSArray canOperDetails = new SFSArray();
			for(BaseOperation canExecuteOperation : canExecuteOperations) {
				if(!canExecuteOperation.canExecute()) continue;
				if(!receiver.equals(canExecuteOperation.getPlayer())) continue;	// 可执行操作只发送给操作者
				canOperDetails.addSFSObject(getOperDetail(canExecuteOperation, receiver).toSfsObject());
			}
			sfsObject.putSFSArray("canOperDetails", canOperDetails);	
		}
		
		// 打牌后听的牌
		sfsObject.removeElement("discard2TingCards");
		SFSObject tingCardsObj = getDiscard2TingCardsSFSObj(receiver);
		if(tingCardsObj != null) {
			sfsObject.putSFSObject("discard2TingCards", tingCardsObj);
		}
		
		MessageHelper.sendMsg(roomExt, MessageContants.SynOperCardRes, sfsObject, receiver);
	}
	
	private SFSObject getDiscard2TingCardsSFSObj(MahjongPlayer player) {
		Map<Integer, DiscardTingCards> discard2TingCards = player.getMjPlayer().getHuChecker().getDiscard2TingCards();
		if(discard2TingCards == null || discard2TingCards.isEmpty()) return null;
		
		SFSObject tingCardsObj = new SFSObject();
		for(DiscardTingCards discardTingCards : discard2TingCards.values()) {
			tingCardsObj.putIntArray(String.valueOf(discardTingCards.getDiscard()), discardTingCards.getTingCards());
		}
		return tingCardsObj;
	}
	
	/*
	 * 同步指定操作，当前仅用于一炮多响时发送胡牌消息
	 */
	public void syncOperCardRes(BaseOperation oper) {
		for(MahjongPlayer receiver : room.getAllPlayers()) {
			SFSObject sfsObject = new SFSObject();
			SFSArray operDetails = new SFSArray();
			operDetails.addSFSObject(getOperDetail(oper, receiver).toSfsObject());
			sfsObject.putSFSArray("operDetails", operDetails);
			MessageHelper.sendMsg(roomExt, MessageContants.SynOperCardRes, sfsObject, receiver);
		}
	}

	/*
	 * 过牌后如果有更多可执行的操作，同步给操作者
	 */
	public void syncCanOperRes() {
		List<BaseOperation> canExecuteOperations = room.getEngine().getOperManager().getCurrentCanExecuteOperations();
		if(canExecuteOperations == null || canExecuteOperations.isEmpty()) return;
		
		for(MahjongPlayer receiver : room.getAllPlayers()) {
			// 可执行操作
			SFSArray canOperDetails = new SFSArray();
			for(BaseOperation canExecuteOperation : canExecuteOperations) {
				if(!canExecuteOperation.canExecute()) continue;
				if(!receiver.equals(canExecuteOperation.getPlayer())) continue;	// 可执行操作只发送给操作者
				canOperDetails.addSFSObject(getOperDetail(canExecuteOperation, receiver).toSfsObject());
			}
			if(canOperDetails.size() == 0) continue;
			
			SFSObject sfsObject = new SFSObject();
			sfsObject.putSFSArray("canOperDetails", canOperDetails);
			
			MessageHelper.sendMsg(roomExt, MessageContants.SynCanOperRes, sfsObject, receiver);
		}
	}

	public void syncSetEnd() {
		int time = (int) (System.currentTimeMillis() / 1000);
		// 发送结束消息
		SFSObject setEndObj = new SFSObject();
		SFSArray userEndInfos = new SFSArray();
		for (MahjongPlayer player : room.getAllPlayers()) {
			PlayerSetResult playerSetResult = room.getEngine().getCalculator().getPlayerSetResult(player);
			userEndInfos.addSFSObject(playerSetResult.toSFSObject());
		}
		setEndObj.putInt("time", time);
		setEndObj.putSFSArray("ret", userEndInfos);
		MessageHelper.sendMsgToAll(roomExt, MessageContants.SynSetEnd, setEndObj, room.getAllPlayers());
	}
	
	/*
	 * 发送整场结束消息
	 */
	public void syncGameEnd() {
		SFSObject gameEndObj = new SFSObject();
		SFSArray userGameEnds = new SFSArray();
		List<MahjongPlayer> players = room.getAllPlayers();
		for (MahjongPlayer player : players) {
			userGameEnds.addSFSObject(player.getGameEndInfo());
		}
		gameEndObj.putSFSArray("ret", userGameEnds);
		gameEndObj.putInt("finalSet", room.getCurSet());
		gameEndObj.putInt("time", (int) (System.currentTimeMillis() / 1000));
		MessageHelper.sendMsgToAll(roomExt, MessageContants.SynEnd, gameEndObj, players);
	}
	
}
