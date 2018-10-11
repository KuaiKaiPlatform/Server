package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class WuTong {

	/*
	 * 检查是否符合五通牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, almightyCardNum);
		Map<Integer, Integer> remain2count = PaiXinHelper.countCardsOfRemain(cards);	// 各牌值的张数
		for(int count : remain2count.values()) {
			if(count >= 5) return true;
		}
		return false;
	}
	
}
