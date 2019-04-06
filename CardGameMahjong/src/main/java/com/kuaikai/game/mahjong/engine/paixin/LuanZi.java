package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.msg.pb.CardTypePB.CardType;

public class LuanZi {
	
	/*
	 * 检查是否符合乱字牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, almightyCardNum);
		Map<CardType, List<Integer>> type2cards = PaiXinHelper.groupCardsByType(cards);
		type2cards.remove(CardType.ALMIGHTY);
		return type2cards.size() == 1 && type2cards.containsKey(CardType.ZI);
	}
	
}
