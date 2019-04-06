package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.msg.pb.CardTypePB.CardType;

public class ShiYiZhi {

	/*
	 * 检查是否符合十一支牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, almightyCardNum);
		
		// 按牌的类型进行分组，万、条、筒、风、万能牌等
		Map<CardType, List<Integer>> type2Cards = PaiXinHelper.groupCardsByType(cards);	
		for(List<Integer> typeCards : type2Cards.values()) {
			if(typeCards.size() >= 11) return true;	// 任一牌类型达到11支
		}
		return false;
	}
	
}
