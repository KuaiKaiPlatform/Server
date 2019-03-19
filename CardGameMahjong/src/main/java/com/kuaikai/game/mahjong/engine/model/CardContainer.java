package com.kuaikai.game.mahjong.engine.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.kuaikai.game.mahjong.engine.constants.OperType;

public class CardContainer {

	protected MahjongPlayer player;
	
	protected List<MJCard> handCards = new ArrayList<MJCard>();
	protected List<CardGroup> cardGroups;
	protected List<MJCard> discards = new ArrayList<MJCard>();
	
	public CardContainer(MahjongPlayer player) {
		this.player = player;
	}

	/*
	 * 发牌
	 */
	public void dealCards(List<Integer> cards) {
		handCards.clear();
		for(int card : cards) {
			handCards.add(MahjongFactory.createMJCard(card, player));
		}
	}

	/*
	 * 摸牌
	 */
	public void addHandCard(MJCard card) {
		handCards.add(card);
	}
	
	public List<MJCard> getHandCards() {
		return handCards;
	}
	
	public MJCard findHandCard(int value) {
		for(MJCard hand : handCards) {
			if(hand.getValue() == value) return hand;
		}
		return null;
	}

	public List<MJCard> findHandCards(int value) {
		List<MJCard> result = new ArrayList<MJCard>();
		for(MJCard hand : handCards) {
			if(hand.getValue() == value) {
				result.add(hand);
			}
		}
		return result;
	}

	public MJCard findLastHandCard() {
		if(handCards.size() == 0) return null;
		return handCards.get(handCards.size() - 1);
	}
	
	/*
	 * 删除并返回手牌中所有指定数量和牌值的牌
	 */
	public List<MJCard> removeHandCards(int value, int count) {
		List<MJCard> result = new ArrayList<MJCard>();
		if(count > 4 || count < 1) return result;
		
		Iterator<MJCard> it = handCards.iterator();
		while(it.hasNext()) {
			MJCard handCard = it.next();
			if(handCard.getValue() == value) {
				result.add(handCard);
				it.remove();
				if(--count == 0) break;
			}
		}
		return result;
	}

	/*
	 * 返回手牌牌值，用于前端
	 */
	public List<Integer> getHandCardValues() {
		List<Integer> cards = new ArrayList<Integer>();
		for(MJCard card : handCards) {
			cards.add(card.getValue());
		}
		Collections.sort(cards);
		return cards;
	}
	
	/*
	 * 增加吃、碰、杠的牌
	 */
	public void addCardGroup(CardGroup cardGroup) {
		if(cardGroups == null) cardGroups = new ArrayList<CardGroup>();
		cardGroups.add(cardGroup);
	}
	
	public List<CardGroup> getCardGroups() {
		return cardGroups;
	}

/*	public SFSArray getCardGroupsSFS() {
		SFSArray result = new SFSArray();
		if(cardGroups == null) return result;
		for(CardGroup group : cardGroups) {
			result.addSFSObject(group.toSFSObject());
		}
		return result;
	}*/
	
	public CardGroup findPengGroup(int card) {
		for(CardGroup group : cardGroups) {
			if(group.checkOperType(OperType.PENG) && group.getTarget().getValue() == card) return group;
		}
		return null;
	}
	
	/*
	 * 打牌
	 */
	public void discard(MJCard card) {
		handCards.remove(card);
		discards.add(card);
	}
	
	/*
	 * 收回刚打出的一张牌
	 */
	public MJCard takeLastDiscard() {
		if(discards.isEmpty()) return null;
		return discards.remove(discards.size()-1);
	}
	
	/*
	 * 返回打出的牌值，用于前端
	 */
	public List<Integer> getDiscardValues() {
		List<Integer> result = new LinkedList<Integer>();
		for(MJCard discard : discards) {
			result.add(discard.getValue());
		}
		return result;
	}

	public void clear() {
		handCards.clear();
		discards.clear();
		if(cardGroups != null) cardGroups.clear();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		stringBuilder.append("handCards=").append(handCards).append(",");
		stringBuilder.append("discards=").append(discards).append(",");
		stringBuilder.append("cardGroups=").append(cardGroups).append(",");
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
	
}
