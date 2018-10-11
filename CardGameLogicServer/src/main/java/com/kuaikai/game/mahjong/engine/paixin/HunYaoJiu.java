package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class HunYaoJiu {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	/*
	 * 检查是否符合混幺九牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {

		for(MJCard c : handCards) {
			if(c.getValue() == almightyCardNum && c.isValidAlmighty()) continue;
			if(!c.isZi() || !c.isYaoJiu()) return false;
		}
		
		if(groupList == null) return true;
		
		for(CardGroup group : groupList) {
			//if(!group.isValid()) continue;
			for(MJCard c : group.getCards()) {
				if(!c.isZi() || !c.isYaoJiu()) return false;		
			}
		}
		return true;
	}
	
}
