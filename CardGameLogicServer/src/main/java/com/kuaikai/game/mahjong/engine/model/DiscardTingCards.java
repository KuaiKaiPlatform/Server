package com.kuaikai.game.mahjong.engine.model;

import java.util.LinkedList;
import java.util.List;

/*
 * 打一张牌，可听的牌列表
 */
public class DiscardTingCards {
	
	private int discard;
	private List<Integer> tingCards = new LinkedList<Integer>();
	
	public DiscardTingCards(int discard) {
		this.discard = discard;
	}
	
	public void addTingCard(int tingCard) {
		tingCards.add(tingCard);
	}

	public int getDiscard() {
		return discard;
	}

	public List<Integer> getTingCards() {
		return tingCards;
	}
	
}
