package com.kuaikai.game.common.model;

import java.util.LinkedList;
import java.util.List;

public class Player {
	
	private User user;
	
	// 玩家座位，从1开始，对应麻将桌东向
	private int seat;

	// 是否准备
	private boolean prepared = false;
	
	// 是否离线
	private boolean offline = false;

	// 各项分数
	private List<Integer> points;

	public int getId() {
		return user==null?0:user.getId();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public boolean isPrepared() {
		return prepared;
	}

	public void setPrepared(boolean prepared) {
		this.prepared = prepared;
	}

	public boolean isOffline() {
		return offline;
	}

	public void setOffline(boolean offline) {
		this.offline = offline;
	}

	public List<Integer> getPoints() {
		return points;
	}

	public void addPoint(int point) {
		if(points == null) points = new LinkedList<Integer>();
		points.add(point);
	}
	
	
}
