package com.kuaikai.game.mahjong.engine.paixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class TongShu {
	
	/*
	 * 返回指定牌面的通数：key是几通，value是该通的数目，如有2个五通，key=5，value=2
	 */
	public static Map<Integer, Integer> check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		Map<Integer, Integer> tongShu = new HashMap<Integer, Integer>();
		
		List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, almightyCardNum);
		Map<Integer, Integer> remain2count = PaiXinHelper.countCardsOfRemain(cards);	// 各牌值的张数
		for(int tong : remain2count.values()) {
			if(tong < 5) continue;
			Integer shu = tongShu.get(tong);
			if(shu == null) {
				tongShu.put(tong, 1);
			} else {
				tongShu.put(tong, shu+1);
			}
		}
		return tongShu;
	}
	
}
