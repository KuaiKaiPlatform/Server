package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class BaZhi {
	
	/*
	 * 检查是否符合八支牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, almightyCardNum);
		
		// 按牌的类型进行分组，万、条、筒、风、万能牌等
		Map<Mahjong.CardType, List<Integer>> type2Cards = PaiXinHelper.groupCardsByType(cards);	
		for(List<Integer> typeCards : type2Cards.values()) {
			if(typeCards.size() >= 8) return true;	// 任一牌类型达到8支
		}
		return false;
	}
	
	/*
	 * 返回指定牌面的支数
	 */
/*	public static int getZhiShu(List<MJCard> handCards, List<CardGroup> groupList, int almightyCardNum) {
		List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, almightyCardNum);
		
		// 按牌的类型进行分组，万、条、筒、风、万能牌等
		Map<MJCard.CardType, List<Integer>> type2Cards = PaiXinHelper.groupCardsByType(cards);
		
		int zhiShu = 0;
		for(List<Integer> typeCards : type2Cards.values()) {
			zhiShu = Math.max(zhiShu, typeCards.size());	// 取牌数大的类型
		}
		return zhiShu;
	}
	*/
}
