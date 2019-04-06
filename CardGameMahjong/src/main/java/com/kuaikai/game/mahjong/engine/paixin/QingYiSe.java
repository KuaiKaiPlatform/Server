package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.msg.pb.CardTypePB.CardType;

public class QingYiSe {
	
	/*
	 * 检查是否符合清一色牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		
		CardType type = null;
		for(MJCard c : handCards) {
			if(c.getValue() == almightyCardNum && c.isValidAlmighty()) continue;
			if(!c.isWanTiaoTong()) return false;
			if(type == null) {
				type = c.getCardType();
				continue;
			}
			if(!type.equals(c.getCardType())) {
				return false;
			}
		}
		
		if(groupList == null) return true;
		
		for (CardGroup cg : groupList) {
			//if(!cg.isValid()) continue;
			List<MJCard> cardsList = cg.getCards();
			CardType openType = cardsList.get(0).getCardType();
			if(!openType.equals(type)) {
				return false;
			}
		}
		
		return true;
	}
	
}
