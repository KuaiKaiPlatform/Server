package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class WuZi {
	
	/*
	 * 检查是否符合无字牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		for(MJCard c : handCards) {
			if(c.getValue() == almightyCardNum && c.isValidAlmighty()) continue;
			if(c.isZi()) return false;
		}
		
		if(groupList == null) return true;
		
		for(CardGroup group : groupList) {
			//if(!group.isValid()) continue;
			for(MJCard c : group.getCards()) {
				if(c.isZi()) return false;
			}
		}
		return true;
	}
	
}
