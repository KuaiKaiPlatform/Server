package com.kuaikai.game.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;

public class Desk {
	
	private int deskId;
	// 玩家id到玩家对象map
	private Map<Integer, Player> id2Player = new HashMap<>();
	// 玩家座位到玩家对象map
	private Map<Integer, Player> seat2Player = new HashMap<>();
	// 玩家加入房间的顺序
	private List<Player> players = new ArrayList<>();
	
	private List<Integer> pids = new LinkedList<Integer>();
	
	private GameStatus status = GameStatus.WAITING;
	
	public Desk() {}
	
	public Desk(int id) {
		this.deskId = id;
	}
	
	public int getDeskId() {
		return deskId;
	}

	public void addPlayer(Player player) {
		if(!players.contains(player)) {
			pids.add(player.getId());
			players.add(player);
			id2Player.put(player.getId(), player);
			seat2Player.put(player.getSeat(), player);
		}
	}
	
	public List<Integer> getPids() {
		return pids;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayerById(int pid) {
		return id2Player.get(pid);
	}
	
	public boolean isFull() {
		return players.size() >= 4;
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
	
}
