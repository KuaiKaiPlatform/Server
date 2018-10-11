package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class HunYiSe {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	/*
	 * 检查是否符合混一色牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		Mahjong.CardType type = null;
		for(MJCard c : handCards) {
			if(c.getValue() == almightyCardNum && c.isValidAlmighty()) continue;
			Mahjong.CardType handType = c.getCardType();
			if(handType.equals(Mahjong.CardType.ZI)) continue;
			if(type == null) {
				type = c.getCardType();
				continue;
			}
			if(!handType.equals(type)) {
				//if(logger.isDebugEnabled()) logger.debug("hand type not matched:" +  c.getCardNum());
				return false;
			}
		}
		
		if(groupList == null) return true;
		
		for (CardGroup cg : groupList) {
			//if(!cg.isValid()) continue;
			List<MJCard> cardsList = cg.getCards();
			Mahjong.CardType openType = cardsList.get(0).getCardType();
			if(openType.equals(Mahjong.CardType.ZI)) continue;
			if(!openType.equals(type)) {
				//if(logger.isDebugEnabled()) logger.debug("open type not matched:" + cardsList.get(0).getCardNum());
				return false;
			}
		}
		
		return true;
	}
	
}
