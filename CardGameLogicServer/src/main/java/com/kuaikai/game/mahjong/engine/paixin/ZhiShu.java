package com.kuaikai.game.mahjong.engine.paixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class ZhiShu {
	
	/*
	 * 返回指定牌面各花色的支数
	 */
	public static Map<Mahjong.CardType, Integer> check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		Map<Mahjong.CardType, Integer> result = new HashMap<Mahjong.CardType, Integer>();
		List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, almightyCardNum);
		
		// 按牌的类型进行分组，万、条、筒、风、万能牌等
		Map<Mahjong.CardType, List<Integer>> type2Cards = PaiXinHelper.groupCardsByType(cards);
		for(Map.Entry<Mahjong.CardType, List<Integer>> entry : type2Cards.entrySet()) {
			List<Integer> typeCards = entry.getValue();
			result.put(entry.getKey(), typeCards.size());
		}
		return result;
	}

	/*
	 * 返回各花色的最大支数
	 */
	public static int getMax(Map<Mahjong.CardType, Integer> type2Count) {
		int zhiShu = 0;
		if(type2Count != null && !type2Count.isEmpty()) {
			for(int count : type2Count.values()) {
				zhiShu = Math.max(zhiShu, count);	// 取支数大的
			}
		}
		return zhiShu;
	}
	
}
