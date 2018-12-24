package com.kuaikai.game.common.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.play.CardGameSetting;

public class Desk {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Desk.class);
	
	protected int deskId;
	protected int clubId;
	
	// 玩家id到玩家对象map
	protected Map<Integer, Player> id2Player = new HashMap<>();
	
	// 玩家座位到玩家对象map
	protected Map<Integer, Player> seat2Player = new HashMap<>();
	
	protected GameRule rule = GameRule.GUO_ZI;
	
	// 所有规则设置
	protected AttrsModel setting = new AttrsModel();
	
	// 客户端显示的规则设置
	protected AttrsModel clientSetting = new AttrsModel();
	
	// 当前状态
	protected GameStatus status = GameStatus.WAITING;
	
	// 当前局数
	protected int curSet;
	
	public Desk() {}
	
	public Desk(int id) {
		this.deskId = id;
	}
	
	public int getDeskId() {
		return deskId;
	}
	
	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	
	public String getKey() {
		StringBuilder sb = new StringBuilder().append(this.getDeskId());
		if(clubId > 0) sb.append("-").append(this.getClubId());
		return sb.toString();
	}
	
	public void addPlayer(Player player) {
		if(!id2Player.containsValue(player)) {
			id2Player.put(player.getId(), player);
			seat2Player.put(player.getSeat(), player);
		}
	}
	
	public Collection<Integer> getPids() {
		return id2Player.keySet();
	}
	
	public Collection<Player> getPlayers() {
		return id2Player.values();
	}

	public Player getPlayerById(int pid) {
		return id2Player.get(pid);
	}

	public Player getPlayerBySeat(int seat) {
		return seat2Player.get(seat);
	}
	
	public int getPlayerNum() {
		return id2Player.size();
	}
	
	public boolean isFull() {
		return id2Player.size() >= setting.getInt(CardGameSetting.TOTAL_PLAYER);
	}
	
	public GameRule getRule() {
		return rule;
	}

	public void setRule(GameRule rule) {
		this.rule = rule;
	}

	public AttrsModel getSetting() {
		return setting;
	}

	public void setSetting(AttrsModel setting) {
		this.setting = setting;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public int getCurSet() {
		return curSet;
	}

	public void incrCurSet() {
		this.curSet++;
	}

	public void clear() {
		id2Player.clear();
		seat2Player.clear();
		setting.clear();
	}
	
	public boolean checkStatus(GameStatus status) {
		return status.equals(this.status);
	}
	
	public boolean canJoin() {
		return this.checkStatus(GameStatus.WAITING) && !this.isFull();
	}
	
	/**
	 * 开局：玩家总数达到最少开局人数，并且所有玩家都准备好
	 * 
	 */
	public boolean canStart() {
		LOGGER.debug("Desk.canStart@checking desk={}", this.getKey());
		if(id2Player.size() < setting.getInt(CardGameSetting.MIN_PLAYER)) return false;
		for(Player p : id2Player.values()) {
			if(!p.isPrepared()) return false;
		}
		return true;
	}
	
	public boolean hasClub() {
		return clubId > 0;
	}
	
	public boolean exists() {
		return hasClub() && deskId > 0;
	}
	
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
