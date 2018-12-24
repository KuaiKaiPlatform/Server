package com.kuaikai.game.common.model;

import java.util.Map;

import com.kuaikai.game.common.redis.ClubRedis;
import com.kuaikai.game.common.utils.CollectionUtils;

public class Club {
	
	private int id;
	private String name;
	private String head;
	private int total;
	private int ownerId;
	
	public Club(Map<String, String> map) {
		this.id = CollectionUtils.getMapInt(map, ClubRedis.FIELD_CLUB_ID);
		this.name = CollectionUtils.getMapStr(map, ClubRedis.FIELD_NAME);
		this.total = CollectionUtils.getMapInt(map, ClubRedis.FIELD_TOTAL);
		this.ownerId = CollectionUtils.getMapInt(map, ClubRedis.FIELD_OWNER_ID);
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

	/**
	 * 是否为大众竞技场
	 * @return
	 */
	public boolean isPub() {
		return ownerId == 0;
	}
	
	
}
