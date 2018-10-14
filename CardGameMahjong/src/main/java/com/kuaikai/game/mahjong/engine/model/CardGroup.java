package com.kuaikai.game.mahjong.engine.model;

import java.util.ArrayList;
import java.util.List;

public class CardGroup {
	
	protected MahjongPlayer player;
	protected int operType;
	protected MJCard target;		// 吃、碰、杠的目标牌
	protected List<MJCard> cards = new ArrayList<MJCard>();	// 所有牌：吃或碰三张牌，杠四张牌

	protected CardGroup(MahjongPlayer player, int operType, List<MJCard> cards, MJCard target) {
		this.player = player;
		this.operType = operType;
		this.target = target;
		this.cards.addAll(cards);
	}

	public int getOperType() {
		return operType;
	}

	public void setOperType(int operType) {
		this.operType = operType;
	}
	
	public boolean checkOperType(int operType) {
		return this.operType == operType;
	}

	public MJCard getTarget() {
		return target;
	}

	public void setTarget(MJCard target) {
		this.target = target;
	}

	public List<MJCard> getCards() {
		return cards;
	}

/*	public SFSObject toSFSObject() {
		SFSObject data = new SFSObject();
		data.putInt("operType", operType);
		List<Integer> cardList = new ArrayList<Integer>();
		for(MJCard card : cards) {
			cardList.add(card.getValue());
		}
		data.putIntArray("cards", cardList);
		data.putInt("target", target.getValue());
		return data;
	}*/
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("operType=").append(operType).append(",");
		sb.append("target=").append(target).append(",");
		sb.append("cards=").append(cards).append(",");
		sb.append("}");
		return sb.toString();
	}
	
}