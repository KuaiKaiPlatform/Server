package com.kuaikai.game.mahjong.engine.paixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class SiHe {
	
	/*
	 * 检查是否符合四核牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		Map<Integer, Integer> cardCount = PaiXinHelper.countCards(handCards, almightyCardNum);
		List<Integer> cardList = PaiXinHelper.getHandCardsList(handCards, almightyCardNum);
		
		for(Map.Entry<Integer, Integer> entry : cardCount.entrySet()) {
			if(entry.getValue() == 4) {	// 有四张一样的手牌，去掉三张后还能胡牌
				List<Integer> cards = new ArrayList<Integer>();
				cards.addAll(cardList);
				for(int i=0; i<3; i++) cards.remove(entry.getKey());
				if(BiaoZhunHu.check(cards)) return true;
			}
		}
		
		if(groupList == null) return false;
			
		for(CardGroup cg : groupList) {
			if(!cg.checkOperType(OperType.PENG)) continue;
			int cardNum = cg.getCards().get(0).getValue();
			if(cardCount.containsKey(cardNum)) return true;
		}
		
		return false;
	}
	
}
