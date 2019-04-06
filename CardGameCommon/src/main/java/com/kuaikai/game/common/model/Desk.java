package com.kuaikai.game.common.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.redis.ClubDeskRedis;

public class Desk {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Desk.class);
	
	protected long deskId;
	protected int clubId;
	
	// 玩家id到玩家对象map
	protected Map<Integer, Player> id2Player = new HashMap<>();
	
	// 玩家座位到玩家对象map
	protected Map<Integer, Player> seat2Player = new HashMap<>();
	
	protected GameRule rule;
	
	// 所有规则设置
	protected AttrsModel setting = new AttrsModel();
	
	// 客户端显示的规则设置
	protected AttrsModel clubSetting = new AttrsModel();
	
	// 当前状态
	protected GameStatus status = GameStatus.WAITING;
	
	// 当前局数
	protected int curSet;
	
	// 庄家
	protected Player banker;
	
	public Desk() {}
	
	public Desk(long id) {
		this.deskId = id;
	}
	
	public long getDeskId() {
		return deskId;
	}
	
	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	
	public String getKey() {
		return Desk.getKey(getClubId(), getDeskId());
	}
	
	public static String getKey(int clubId, long deskId) {
		StringBuilder sb = new StringBuilder().append(deskId);
		if(clubId > 0) sb.append("-").append(clubId);
		return sb.toString(); 
	}
	
	public void addPlayer(Player player) {
		if(!id2Player.containsValue(player)) {
			id2Player.put(player.getId(), player);
			seat2Player.put(player.getSeat(), player);
			player.setDesk(this);
		}
	}
	
	public Set<Integer> getPids() {
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
		int totalPlayer = setting.getInt(CardGameSetting.TOTAL_PLAYER);
		return isFull(totalPlayer, id2Player.size());
	}

	public static boolean isFull(int totalPlayer, int num) {
		return totalPlayer > 0 && num >= totalPlayer;
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

	public AttrsModel getClubSetting() {
		return clubSetting;
	}

	public void copyClubSetting() {
		setting.copy(clubSetting);
	}
	
	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
		ClubDeskRedis.putStatus(clubId, deskId, status.getNumber());
	}

	public int getCurSet() {
		return curSet;
	}

	public void setCurSet(int curSet) {
		this.curSet = curSet;
	}

	public void incrCurSet() {
		this.curSet++;
	}

	public Player getBanker() {
		return banker;
	}

	public void setBanker(Player banker) {
		this.banker = banker;
	}
	
	public int getBankerId() {
		return banker == null?0:banker.getId();
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
