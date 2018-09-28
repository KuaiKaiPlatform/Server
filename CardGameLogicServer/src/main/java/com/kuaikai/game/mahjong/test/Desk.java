package com.kuaikai.game.mahjong.test;

import java.util.LinkedList;
import java.util.List;

public class Desk {
	
	private Integer id;
	private List<Integer> pids = new LinkedList<Integer>();
	
	public Desk(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public List<Integer> getPids() {
		return pids;
	}

	public void addPlayer(int pid) {
		if(!pids.contains(pid)) pids.add(pid);
	}
	
	public boolean isFull() {
		return pids.size() >= 4;
	}
	
	public void clear() {
		pids.clear();
	}
	
}
