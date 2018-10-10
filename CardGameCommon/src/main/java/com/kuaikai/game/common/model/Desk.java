package com.kuaikai.game.common.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;

public class Desk {
	
	protected int deskId;
	protected int clubId;
	
	// 玩家id到玩家对象map
	protected Map<Integer, Player> id2Player = new HashMap<>();
	// 玩家座位到玩家对象map
	protected Map<Integer, Player> seat2Player = new HashMap<>();
	// 玩家加入房间的顺序
	protected List<Player> players = new ArrayList<>();
	
	protected GameRule rule = GameRule.GUO_ZI;
	
	protected GameStatus status = GameStatus.WAITING;
	
	protected AttrsModel setting = new AttrsModel();
	
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
		if(!players.contains(player)) {
			players.add(player);
			id2Player.put(player.getId(), player);
			seat2Player.put(player.getSeat(), player);
		}
	}
	
	public Collection<Integer> getPids() {
		return id2Player.keySet();
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayerById(int pid) {
		return id2Player.get(pid);
	}
	
	public boolean isFull() {
		return false;
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

	public void clear() {
		players.clear();
	}
	
	public boolean checkStatus(GameStatus status) {
		return status.equals(this.status);
	}
	
	public boolean canJoin() {
		return this.checkStatus(GameStatus.WAITING) && !this.isFull();
	}
	
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
