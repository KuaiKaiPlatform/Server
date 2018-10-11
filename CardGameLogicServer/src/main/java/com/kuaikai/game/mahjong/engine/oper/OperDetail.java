package com.kuaikai.game.mahjong.engine.oper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.smartfoxserver.v2.entities.data.SFSObject;

public class OperDetail {

	private int uid;				// 操作者id
	private int operType;			// 操作类型（摸、打、吃、碰、杠、听、胡等）
	private List<Integer> cards;	// 用于操作的牌，如：二万和四万，用于吃三万
	private int target;				// 目标牌，如被打的八条、被吃的三万等
	private int remainCards;		// 剩余牌数
	private int fromUid;			// 吃、碰、杠、胡的目标玩家ID
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getOperType() {
		return operType;
	}
	public void setOperType(int operType) {
		this.operType = operType;
	}
	public List<Integer> getCards() {
		return cards;
	}
	public void addCards(Collection<Integer> cards) {
		if(cards == null) return;
		if(this.cards == null) this.cards = new LinkedList<Integer>();
		this.cards.addAll(cards);
	}
	public void addCard(int card) {
		if(this.cards == null) this.cards = new LinkedList<Integer>();
		this.cards.add(card);
	}
	public void clearCards() {
		if(cards != null) cards.clear();
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getRemainCards() {
		return remainCards;
	}
	public void setRemainCards(int remainCards) {
		this.remainCards = remainCards;
	}
	public int getFromUid() {
		return fromUid;
	}
	public void setFromUid(int fromUid) {
		this.fromUid = fromUid;
	}
	
	public SFSObject toSfsObject() {
		SFSObject obj = new SFSObject();
		obj.putInt("uid", uid);
		obj.putInt("operType", operType);
		if(cards != null) {
			obj.putIntArray("cards", cards);
		}
		obj.putInt("target", target);
		obj.putInt("remainCards", remainCards);
		obj.putInt("fromUid", fromUid);
		return obj;
	}
	
}
