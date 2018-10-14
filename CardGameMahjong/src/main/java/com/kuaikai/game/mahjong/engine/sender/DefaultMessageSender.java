package com.kuaikai.game.mahjong.engine.sender;

import java.util.ArrayList;
import java.util.List;

import com.kuaikai.game.common.play.GameDesk;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.common.play.MessageSender;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;

public class DefaultMessageSender extends MessageSender {
	
	public DefaultMessageSender(GameDesk desk) {
		super(desk);
	}
	
	private MahjongDesk getDesk() {
		return (MahjongDesk)desk;
	}
	
	/**
	 * 同步牌桌信息
	 */
	public void syncSetInit(GamePlayer p) {
		List<GamePlayer> receivers = new ArrayList<GamePlayer>();
		if(p == null)
			receivers.addAll(desk.getAllPlayers());
		else
			receivers.add(p);
		
		List<BaseOperation> canExecuteOperations = getDesk().getEngine().getOperManager().getCurrentCanExecuteOperations();
		for(GamePlayer receiver : receivers) {
/*			SFSObject initSFSObject = getSetStartObj(receiver);
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
			
			MessageHelper.sendMsg(roomExt, MessageContants.Init, initSFSObject, receiver);*/

		}
		
	}

	/*
	 * 同步玩家最近的操作和可执行的操作
	 */
	public void syncOperCardRes() {
		for(GamePlayer receiver : desk.getAllPlayers()) {
			//syncOperCardRes(receiver);
		}
	}
	
	/*
	 * 同步指定操作，当前仅用于一炮多响时发送胡牌消息
	 */
	public void syncOperCardRes(BaseOperation oper) {
		for(GamePlayer receiver : desk.getAllPlayers()) {
			
		}
	}
	
	/*
	 * 过牌后如果有更多可执行的操作，同步给操作者
	 */
	public void syncCanOperRes() {
		
	}
	
	public void syncSetEnd() {
/*		int time = (int) (System.currentTimeMillis() / 1000);
		// 发送结束消息
		SFSObject setEndObj = new SFSObject();
		SFSArray userEndInfos = new SFSArray();
		for (MahjongPlayer player : desk.getAllPlayers()) {
			PlayerSetResult playerSetResult = desk.getEngine().getCalculator().getPlayerSetResult(player);
			userEndInfos.addSFSObject(playerSetResult.toSFSObject());
		}
		setEndObj.putInt("time", time);
		setEndObj.putSFSArray("ret", userEndInfos);
		MessageHelper.sendMsgToAll(roomExt, MessageContants.SynSetEnd, setEndObj, desk.getAllPlayers());*/
	}
	
	/*
	 * 发送整场结束消息
	 */
	public void syncGameEnd() {
/*		SFSObject gameEndObj = new SFSObject();
		SFSArray userGameEnds = new SFSArray();
		List<MahjongPlayer> players = desk.getAllPlayers();
		for (MahjongPlayer player : players) {
			userGameEnds.addSFSObject(player.getGameEndInfo());
		}
		gameEndObj.putSFSArray("ret", userGameEnds);
		gameEndObj.putInt("finalSet", desk.getCurSet());
		gameEndObj.putInt("time", (int) (System.currentTimeMillis() / 1000));
		MessageHelper.sendMsgToAll(roomExt, MessageContants.SynEnd, gameEndObj, players);*/
	}
	
}
