package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class ZiYiSe {
	
	/*
	 * 检查是否符合字一色牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		for(MJCard c : handCards) {
			if(c.getValue() == almightyCardNum && c.isValidAlmighty()) continue;
			if(!c.isZi()) return false;
		}

		if(groupList == null) return true;
		
		for (CardGroup cg : groupList) {
			//if(!cg.isValid()) continue;
			List<MJCard> cardsList = cg.getCards();
			for(MJCard c : cardsList) {
				if(!c.isZi()) return false;
			}
		}
		
		return true;
	}
	
}
