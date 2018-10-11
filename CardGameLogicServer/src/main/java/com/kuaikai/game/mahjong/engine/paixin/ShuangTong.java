package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class ShuangTong {

	/*
	 * 检查是否符合双通牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, almightyCardNum);
		Map<Integer, Integer> remain2count = PaiXinHelper.countCardsOfRemain(cards);	// 各牌值的张数
		int counter = 0;
		for(int count : remain2count.values()) {
			if(count >= 5) counter++;
		}
		return counter >= 2;
	}
	
}
