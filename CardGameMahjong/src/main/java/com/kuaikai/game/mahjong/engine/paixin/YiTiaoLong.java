package com.kuaikai.game.mahjong.engine.paixin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class YiTiaoLong {
	
	/*
	 * 检查是否符合一条龙牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {		
		int size = handCards.size();
		if(size < 11){
			return false;
		}

		//把同一种颜色的123 456 789 移除再看是否是胡牌的
		for(Mahjong.CardType cardType : Mahjong.CardType.values()) {
			if(!Mahjong.CardType.isWanTiaoTong(cardType)) continue;
			List<Integer> cards = getRemainCards(handCards, cardType);
			if(cards == null) continue;
			if(BiaoZhunHu.check(cards)) return true;	
		}
		
		return false;
	}
	
	/**
	 * 返回手牌去除一条龙后剩余的牌
	 * @param handCards
	 * @param cardType
	 * @return
	 */
	private static List<Integer> getRemainCards(List<MJCard> handCards, Mahjong.CardType cardType){
		//把同一种颜色的123 456 789 移除
		List<Integer> cards = new ArrayList<Integer>();
		for(MJCard card : handCards) {	// 所有手牌
			cards.add(card.getValue());
		}
		int typeValue = cardType.getValue();
		for(int i=1; i<=9; i++) {
			if(!cards.remove(new Integer(typeValue + i))) return null;	// 删除一条龙
		}
		
		Collections.sort(cards);	// 排序
		return cards;
	}
	
}
