package com.kuaikai.game.common.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.common.redis.ClubRedis;
import com.kuaikai.game.common.utils.CollectionUtils;

public class Club {
	
	private int id;
	private String name;
	private String head;
	private int total;
	private int ownerId;	// 0 表示当前竞技场
	private int totalDesk;	// 私有俱乐部总桌数
	private long deskId;	// 大众俱乐部当前桌号
	
	private List<ClubRule> clubRules = new LinkedList<ClubRule>();
	
	public Club() {}
	
	public Club(Map<String, String> map) {
		this.id = CollectionUtils.getMapInt(map, ClubRedis.FIELD_CLUB_ID);
		this.name = CollectionUtils.getMapStr(map, ClubRedis.FIELD_NAME);
		this.total = CollectionUtils.getMapInt(map, ClubRedis.FIELD_TOTAL);
		this.ownerId = CollectionUtils.getMapInt(map, ClubRedis.FIELD_OWNER_ID);
		this.totalDesk = CollectionUtils.getMapInt(map, ClubRedis.FIELD_TOTAL_DESK);
		this.deskId = CollectionUtils.getMapLong(map, ClubRedis.FIELD_DESK_ID);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getTotalDesk() {
		return totalDesk;
	}
	public void setTotalDesk(int totalDesk) {
		this.totalDesk = totalDesk;
	}
	public long getDeskId() {
		return deskId;
	}
	public void setDeskId(long deskId) {
		this.deskId = deskId;
	}

	public void addClubRule(ClubRule rule) {
		clubRules.add(rule);
	}
	
	/**
	 * 是否为大众竞技场
	 * @return
	 */
	public boolean isPub() {
		return ownerId == 0;
	}
	
	
}
