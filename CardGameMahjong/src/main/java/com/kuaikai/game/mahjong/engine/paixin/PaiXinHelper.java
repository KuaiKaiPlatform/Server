package com.kuaikai.game.mahjong.engine.paixin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class PaiXinHelper {

	protected static final Logger logger = LoggerFactory.getLogger("play");

	public static final int ALMIGHTY_CARD_NUM = 100;	// 将万能牌改为值100，万能牌打出时，其只做本身牌值使用。
	
	// 所有手牌计数
	public static Map<Integer, Integer> countCards(int[] cards, int almightyCardNum) {
		// 计数
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < cards.length; i++) {
			if(cards[i] == almightyCardNum) {	// 将万能牌改为值100
				cards[i] = ALMIGHTY_CARD_NUM;
			}
			
			if (!map.containsKey(cards[i])) {
				map.put(cards[i], 0);
			}
			int count = map.get(cards[i]) + 1;
			map.put(cards[i], count);
		}
		return map;
	}
	
	// 所有手牌计数，将万能牌改为值100，万能牌打出时，只做本身牌值使用。
	public static Map<Integer, Integer> countCards(List<MJCard> cards, int almightyCardNum) {
		// 计数
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (MJCard card : cards) {
			int cardNum = card.getValue();
			if(cardNum == almightyCardNum && card.isValidAlmighty()) {	// 将万能牌改为值100
				cardNum = ALMIGHTY_CARD_NUM;
			}
			if (!map.containsKey(cardNum)) {
				map.put(cardNum, 0);
			}
			int count = map.get(cardNum) + 1;
			map.put(cardNum, count);
			//logger.debug("card=" + card.getCardNum() + ",count=" + count);
		}
		return map;
	}

	// 所有手牌计数
	public static Map<Integer, Integer> countCards(List<Integer> cards) {
		// 计数
		Map<Integer, Integer> cardCount = new HashMap<Integer, Integer>();
		for (int card : cards) {
			int count = 0;
			if (cardCount.containsKey(card)) {
				count = cardCount.get(card);
			}
			cardCount.put(card, count+1);
		}
		return cardCount;
	}

	// key 是几张牌，1-4，value是什么牌有这个张数。
	public static Map<Integer, Set<Integer>> groupCardsByCount(List<Integer> cards) {
		Map<Integer, Set<Integer>> count2Cards = new HashMap<Integer, Set<Integer>>();
		
		int count = 0;
		int current = cards.get(0);
		for (int i=0; i<cards.size(); i++) {
			int card = cards.get(i);
			if (card == current) {
				count++;
			} else {
				Set<Integer> set = count2Cards.get(count);
				if(set == null) {
					set = new HashSet<Integer>();
					count2Cards.put(count, set);
				}
				set.add(current);
				current = card;
				count = 1;
			}
			
			if(i==(cards.size()-1)) {
				Set<Integer> set = count2Cards.get(count);
				if(set == null) {
					set = new HashSet<Integer>();
					count2Cards.put(count, set);
				}
				set.add(current);
			}
			
		}
		return count2Cards;
	}

	// key 是牌值（五万、五条的牌值为5）：1-9，value是这个牌值的张数。
	public static Map<Integer, Integer> countCardsOfRemain(List<Integer> cards) {
		Map<Integer, Integer> remain2Count = new HashMap<Integer, Integer>();
		for (int card : cards) {
			if(!Mahjong.isWanTiaoTong(card)) continue;	// 只统计万条筒
			int remain = Mahjong.getRemain(card);
			Integer count = remain2Count.get(remain);
			if (count == null) {
				remain2Count.put(remain, 1);
			} else {
				remain2Count.put(remain, count+1);
			}
		}
		return remain2Count;
	}
	
	// key 是麻将牌的类型：万，条，筒，字，value是麻将牌列表。
	public static Map<Mahjong.CardType, List<Integer>> groupCardsByType(List<Integer> cards) {
		Map<Mahjong.CardType, List<Integer>> type2Cards = new HashMap<Mahjong.CardType, List<Integer>>();
		for (int card : cards) {
			Mahjong.CardType type = Mahjong.getCardType(card);
			List<Integer> cardsType = type2Cards.get(type);
			if (cardsType == null) {
				cardsType = new ArrayList<Integer>();
				type2Cards.put(type, cardsType);
			}
			cardsType.add(card);
		}
		return type2Cards;
	}
	
	/*
	 * 检查一组麻将牌是否是万、条、筒或万能牌。
	 */
	public static boolean isWanTiaoTong(List<Integer> cards) {
		for(int card : cards) {
			if(card == ALMIGHTY_CARD_NUM) continue; // 万能牌，略过
			Mahjong.CardType cardType = Mahjong.getCardType(card);
			if(!Mahjong.CardType.isWanTiaoTong(cardType)) return false;	// 不是万、条、筒 
		}
		return true;
	}
	
	public static List<Integer> toCardsList(List<MJCard> handCards) {
		List<Integer> cards = new LinkedList<Integer>();
		for (MJCard card : handCards) {
			cards.add(card.getValue());
		}
		Collections.sort(cards);
		return cards;
	}
	
	/*
	 * 返回所有手牌，万能牌转为100
	 */
	public static List<Integer> getHandCardsList(List<MJCard> handCards, int almightyCardNum) {
		List<Integer> cards = new ArrayList<Integer>();
		for (MJCard mjCard : handCards) {
			int cardNum = mjCard.getValue();
			if(cardNum == almightyCardNum && mjCard.isValidAlmighty()) {	// 将万能牌改为值100
				cardNum = PaiXinHelper.ALMIGHTY_CARD_NUM;
			}
			cards.add(cardNum);
		}
		Collections.sort(cards);
		return cards;
	}
	
	/*
	 * 返回指定牌面的所有牌列表（包括手牌、明牌）
	 */
	public static List<Integer> getAllCards(List<MJCard> handCards, List<CardGroup> groupList, int almightyCardNum) {
		List<Integer> cards = PaiXinHelper.getHandCardsList(handCards, almightyCardNum);
		// 加入所有明牌，杠4张，碰3张。
		if(groupList != null) {
			for(CardGroup cg : groupList) {
				//if(!cg.isValid()) continue;
				for(MJCard mjCard : cg.getCards()) {
					cards.add(mjCard.getValue());
				}
			}	
		}
		Collections.sort(cards);
		return cards;
	}
	
	/*
	 * 检查指定麻将牌列表中是否有相同牌
	 */
	public static boolean hasSameCard(List<Integer> cards) {
		Set<Integer> set = new HashSet<Integer>();
		set.addAll(cards);
		if(set.size() != cards.size()) return true;	// 集合数量和列表数量不一致，有相同牌
		return false;
	}

	/*
	 * 返回指定牌面的手牌和吃牌
	 * 
	 */
	public static List<Integer> getHandAndChiCards(List<MJCard> handCards, MJCard mjCard, List<CardGroup> groupList, int almightyCardNum) {
		List<Integer> cards = PaiXinHelper.getHandCardsList(handCards, almightyCardNum);
		// 将吃牌加入到cards中
		if(groupList != null) {
			for (CardGroup group : groupList) {
				if (!group.checkOperType(OperType.CHI))
					continue;
				for (MJCard c : group.getCards()) {
					cards.add(c.getValue());
				}
			}
		}
		Collections.sort(cards);
		return cards;
	}
	
}
