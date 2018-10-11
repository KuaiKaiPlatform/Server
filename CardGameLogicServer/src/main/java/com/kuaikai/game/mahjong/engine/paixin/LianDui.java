package com.kuaikai.game.mahjong.engine.paixin;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class LianDui {
	
	/*
	 * 检查连对的数目（用于七对，不做标准胡检查）：key是几连对，value是该连对的数目，如有2个3连对，key=3，value=2
	 */
	public static Map<Integer, Integer> check(List<MJCard> handCards, MJCard mjCard, List<CardGroup> groupList, int almightyCardNum) {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		
		List<Integer> cards = PaiXinHelper.getHandCardsList(handCards, almightyCardNum);
		Map<Integer, Set<Integer>> count2cards = PaiXinHelper.groupCardsByCount(cards);
		
		// 超过2张的牌
		List<Integer> cards234 = new LinkedList<Integer>();
		if(count2cards.containsKey(2)) cards234.addAll(count2cards.get(2));
		if(count2cards.containsKey(3)) cards234.addAll(count2cards.get(3));
		if(count2cards.containsKey(4)) cards234.addAll(count2cards.get(4));
		Collections.sort(cards234);
		
		checkLianDui(cards, result, count2cards.get(4));
		return result;
	}
	
	private static void checkLianDui(List<Integer> cards, Map<Integer, Integer> result, Set<Integer> cards4) {
		int count = 0, previous = 0;
		for(int i=0; i<cards.size(); i++) {
			int card = cards.get(i);
			if(Mahjong.isZi(card)) break;
			if(previous == 0) {
				previous = card;
				count++;
				continue;
			}
			if(card==(previous+1)) {
				count++;
			} else {
				if(count >= 2) {	// 2连对以上记下
					if(result.containsKey(count)) {
						result.put(count, result.get(count)+1);
					} else {
						result.put(count, 1);
					}
					// 去掉连对，检查剩余的牌
					List<Integer> cardsRemain = new LinkedList<Integer>();
					cardsRemain.addAll(cards);
					for(int j=0; j<count; j++) {
						if(cards4 !=null && cards4.contains(card-j)) {	// 有四张牌，保留
							cards4.remove(card-j);
						} else {
							cardsRemain.remove(new Integer(card-j));
						}
					}
					checkLianDui(cardsRemain, result, cards4);
					break;
				}
				count = 1;
			}
			previous = card;
		}
	}
	
}
