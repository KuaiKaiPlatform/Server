package com.kuaikai.game.common.play;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.GameAttrs;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;

/**
 * 游戏牌桌，定义通用操作
 *
 */
public abstract class GameDesk {

	protected Desk desk;
	
	protected DeskRecord record;
	
	protected MessageSender messageSender;
	
	protected AttrsModel attrs = new AttrsModel();
	
	// 玩家id到玩家对象map
	protected Map<Integer, GamePlayer> id2Player = new HashMap<>();
	
	// 玩家座位到玩家对象map
	protected Map<Integer, GamePlayer> seat2Player = new HashMap<>();
	
	// 庄家
	protected GamePlayer banker;
	
	protected GameDesk(Desk desk) {
		this.desk = desk;
	}
	
	public Desk getDesk() {
		return desk;
	}
	
	public void setDesk(Desk desk) {
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
	
	public GameStatus getStatus() {
		return desk.getStatus();
	}

	public void setStatus(GameStatus status) {
		desk.setStatus(status);
	}
	
	public boolean checkStatus(GameStatus status) {
		return desk.checkStatus(status);
	}
	
	public GamePlayer getBanker() {
		return banker;
	}

	public void setBanker(GamePlayer banker) {
		this.banker = banker;
		desk.setBanker(banker==null?null:banker.getPlayer());
	}
	
	public int getBankerId() {
		return desk.getBankerId();
	}
	
	public abstract GamePlayer initBanker();
	
	public DeskRecord getRecord() {
		return record;
	}

	public void setRecord(DeskRecord record) {
		this.record = record;
	}

	public AttrsModel getAttrs() {
		return attrs;
	}
	
	public abstract void addPlayer(Player player);
	
	public void addPlayer(GamePlayer player) {
		if(!id2Player.containsValue(player)) {
			id2Player.put(player.getId(), player);
			seat2Player.put(player.getSeat(), player);
		}
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
	
	public Set<Integer> getPids() {
		return desk.getPids();
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

	public boolean isFull() {
		return desk.isFull();
	}
	
	public boolean isAllPlayerReady() {
		for (GamePlayer player : this.getAllPlayers()) {
			if (!player.isReady()) {
				return false;
			}
		}
		return true;
	}
	
	public void onSetStart(long startTime) {
		this.getAttrs().put(GameAttrs.SET_START_TIME, startTime);
	}
	
	public void onSetEnd(long endTime) {
		this.getAttrs().put(GameAttrs.SET_END_TIME, endTime);
	}
	
	public void onGameStart(long startTime) {
		this.getAttrs().put(GameAttrs.START_TIME, startTime);
	}
	
	public void onGameEnd(boolean dismiss, long endTime) {
		this.getAttrs().put(GameAttrs.END_TIME, endTime);
	}
	
}
