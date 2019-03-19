package com.kuaikai.game.mahjong.engine.calculator;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ScoreDetail {
	// 分数改变的总原因
	private final int mainType;
	// 分数改变子原因列表
	private List<Integer> subTypes;
	// 最终分数变化
	private int score;
	// 本项是否显示在结算页
	private boolean display = true;
	// 本项是否计入最终分数
	private boolean toPay = true;

	public ScoreDetail(int mainType, int score) {
		this.mainType = mainType;
		this.score = score;
	}
	
	public ScoreDetail(int mainType, int score, boolean display, boolean toPay) {
		this.mainType = mainType;
		this.score = score;
		this.display = display;
		this.toPay = toPay;
	}

	public int getMainType() {
		return mainType;
	}

	public void removeSubType(int subType) {
		if(this.subTypes == null) return;
		this.subTypes.remove(new Integer(subType));
	}

	public void addSubType(int subType) {
		if(this.subTypes == null) this.subTypes = new LinkedList<Integer>();
		this.subTypes.add(subType);
	}

	public void addSubTypes(Collection<Integer> subTypes) {
		if(subTypes == null || subTypes.isEmpty()) return;
		if(this.subTypes == null) this.subTypes = new LinkedList<Integer>();
		this.subTypes.addAll(subTypes);
	}

	public void replaceSubType(Integer from, Integer to) {
		if(this.subTypes == null) return;
		int index = subTypes.indexOf(from);
		if(index >= 0) {
			subTypes.remove(from);
			subTypes.add(index, to);
		}
	}
	
	public List<Integer> getSubTypes() {
		return subTypes;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public void setLost() {
		this.score = -score;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public boolean isToPay() {
		return toPay;
	}

	public void setToPay(boolean toPay) {
		this.toPay = toPay;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{mainType=").append(mainType).append(",");
		stringBuilder.append("subTypes=").append(subTypes).append(",");
		stringBuilder.append("score=").append(score).append(",");
		stringBuilder.append("}");
		return stringBuilder.toString();
	}

/*	public ISFSObject toSFSObject() {
		SFSObject data = new SFSObject();
		data.putInt("mainType", mainType);
		if(subTypes != null) data.putIntArray("subTypes", subTypes);
		data.putInt("score", score);
		return data;
	}*/
	
}
