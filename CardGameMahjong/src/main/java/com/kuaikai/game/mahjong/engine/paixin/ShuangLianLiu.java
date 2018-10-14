package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class ShuangLianLiu {
	
	/*
	 * 检查是否符合双连六牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard mjCard, List<CardGroup> groupList, int almightyCardNum) {		
		List<Integer> cards = PaiXinHelper.getHandAndChiCards(handCards, mjCard, groupList, almightyCardNum);
		int card = LianLiu.checkLianLiu(cards);
		if(card > 0) {	// 已经有一个连六，去掉后检查是否还有连六
			cards.remove(new Integer(card));
			cards.remove(new Integer(card+1));
			cards.remove(new Integer(card+2));
			cards.remove(new Integer(card+3));
			cards.remove(new Integer(card+4));
			cards.remove(new Integer(card+5));
			return LianLiu.checkLianLiu(cards) > 0;
		}
		return false;
	}
	
}
