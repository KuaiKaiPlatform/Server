package com.kuaikai.game.mahjong.engine.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.utils.RandomUtils;

public class CardPool {

	private static final Logger logger = LoggerFactory.getLogger("play");
	
	private MahjongDesk desk;
	private List<Integer> initCards;	// 牌池单张牌
	
	private List<Integer> cards = new ArrayList<Integer>();
	private List<MJCard> discards = new ArrayList<MJCard>();
	private int current = 0;

	public CardPool(MahjongDesk desk, List<Integer> initCards) {
		this.desk = desk;
		this.initCards = initCards;
	}

	/*
	 * 从牌池取出一张牌
	 */
	public int takeNextCard() {
		return isEmpty()?0:cards.get(current++);
	}

	/*
	 * 看牌池中下一张牌
	 */
	public int seeNextCard() {
		return isEmpty()?0:cards.get(current);
	}

	/*
	 * 从牌池取出指定数量的牌
	 */
	public List<Integer> takeNextCards(int count) {
		List<Integer> result = new ArrayList<Integer>();
		for(int i=0; i<count; i++) {
			if(isEmpty()) break;
			result.add(cards.get(current++));
		}
		return result;
	}
	
	/*
	 * 从牌池尾部取出一张牌
	 */
	public int takeTailCard(int delta) {
		int index = cards.size() - delta;
		if(index < 0 || index >= cards.size()) return 0;
		return cards.remove(index);
	}

	/*
	 * 看牌池尾部的一张牌
	 */
	public int seeTailCard(int delta) {
		int index = cards.size() - delta;
		if(index < 0 || index >= cards.size()) return 0;
		return cards.get(index);
	}

	/*
	 * 返回牌池最后一张牌
	 */
	public int getLastCardInPool() {
		return cards.isEmpty()?0:cards.get(cards.size()-1);
	}
	
	public int remainCards() {
		return cards.size() - current;
	}

	/*
	 * 检查是否无牌
	 */
	public boolean isEmpty() {
		return remainCards() <= desk.getEngine().getProcessor().getRemainCardNum();
	}
	
	/*
	 * 洗牌
	 */
	public void shuffle() {
		cards.clear();
		discards.clear();
		current = 0;
		
		List<Integer> cardList = new ArrayList<Integer>();
		for (Integer card : initCards) {
			for(int i=0; i<4; i++) {
				cardList.add(card);
			}
		}
		
		while (cardList.size() > 0) {
			int index = RandomUtils.getRandomInt(0, cardList.size() - 1);
			int item = cardList.remove(index);
			cards.add(item);
		}
	}
	
	/*
	 * 打出一张牌
	 */
	public void discard(MJCard card) {
		discards.add(card);
	}
	
	/*
	 * 收回刚打出的一张牌
	 */
	public MJCard takeLastDiscard() {
		if(discards.isEmpty()) return null;
		MJCard card = discards.remove(discards.size()-1);
		MJCard cardP = card.getPlayer().getCardContainer().takeLastDiscard();
		if(!card.equals(cardP)) {
			logger.error("CardPool.takeLastDiscard@card not matched|desk={}|uid={}|card={}|cardP={}",
					desk.getKey(), card.getPlayer().getId(), card.getValue(), cardP.getValue());
		}
		return card;
	}
	
	public List<MJCard> getDiscards() {
		return discards;
	}
	
}
