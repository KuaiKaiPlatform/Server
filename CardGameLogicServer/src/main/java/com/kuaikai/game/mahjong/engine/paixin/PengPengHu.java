package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class PengPengHu {
	
	/*
	 * 检查是否符合碰碰胡牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		
		if(groupList != null) {
			for (CardGroup group : groupList) {
				// if(!group.isValid()) continue;
				if (group.checkOperType(OperType.CHI))
					return false; // 有吃牌
			}
		}
		
		Map<Integer, Integer> cardCount = PaiXinHelper.countCards(handCards, almightyCardNum);
		int count1 = 0;			// 一张牌计数
		int count2 = 0;			// 二张牌计数
		int countAlmighty = 0;	// 万能牌计数
		for(Map.Entry<Integer, Integer> entry : cardCount.entrySet()) {
			int cardNum = entry.getKey();
			int count = entry.getValue();
			if(cardNum == PaiXinHelper.ALMIGHTY_CARD_NUM) {
				countAlmighty = count;
				continue;
			}
			switch(count) {
			case 1 :
			case 4 : 
				count1++;
				break;
			case 2 :
				count2++;
				break;
			}
		}
		
		int need1 = count1>0?(count1*2-1):0;	// 1张单牌需要1个万能牌，2张单牌需要3个万能牌
		int need2 = count2>1?(count2-1):0;		// 1对子需要0张万能牌，2对子需要1张万能牌，3对子需要2张万能牌
		int need = (count1>0&&count2>0)?need1+need2+1:need1+need2;	// 同时有单牌和对子时，需要多一张万能牌成刻子。
		
		return countAlmighty >= need;
	}
	
}
