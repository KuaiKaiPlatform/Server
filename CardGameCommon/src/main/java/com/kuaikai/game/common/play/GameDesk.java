package com.kuaikai.game.common.play;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;

/**
 * 游戏牌桌，定义通用操作
 *
 */
public abstract class GameDesk {

	protected Desk desk;
	
	protected DeskRecord record;
	
	protected MessageSender messageSender;
	
	private AttrsModel attrs;
	
	// 玩家id到玩家对象map
	protected Map<Integer, GamePlayer> id2Player = new HashMap<>();
	
	// 玩家座位到玩家对象map
	protected Map<Integer, GamePlayer> seat2Player = new HashMap<>();
	
	// 庄家
	protected GamePlayer banker;
	
	protected GameDesk(Desk desk) {
		this.desk = desk;
	}
	
	public GameRule getRule() {
		return desk.getRule();
	}
	
	public AttrsModel getSetting() {
		return desk.getSetting();
	}
	
	public String getKey() {
		return desk.getKey();
	}
	
	public int getCurSet() {
		return desk.getCurSet();
	}
	
	public GamePlayer getBanker() {
		return banker;
	}

	public void setBanker(GamePlayer banker) {
		this.banker = banker;
	}
	
	public int getBankerId() {
		return banker == null?-1:banker.getId();
	}
	
	public DeskRecord getRecord() {
		return record;
	}

	public void setRecord(DeskRecord record) {
		this.record = record;
	}

	public AttrsModel getAttrs() {
		return attrs;
	}

	public Collection<GamePlayer> getAllPlayers() {
		return id2Player.values();
	}
	
	public GamePlayer getPlayerById(int pid) {
		return id2Player.get(pid);
	}

	public GamePlayer getPlayerBySeat(int seat) {
		return seat2Player.get(seat);
	}
	
	public int getPlayerNum() {
		return id2Player.size();
	}
	
	public GamePlayer getNextPlayer(GamePlayer gamePlayer) {
		return getNextPlayer(gamePlayer.player.getSeat());
	}
	
	public GamePlayer getNextPlayer(int seat) {
		int nextSeat = seat % getSetting().getInt(CardGameSetting.TOTAL_PLAYER) + 1;
		GamePlayer player = this.getPlayerBySeat(nextSeat);
		return player == null?getNextPlayer(nextSeat):player;
	}
	
	public MessageSender getMessageSender() {
		return messageSender;
	}
	
	public abstract void onGameStart();
	
	public abstract void onGameEnd(boolean normal);
	
}
