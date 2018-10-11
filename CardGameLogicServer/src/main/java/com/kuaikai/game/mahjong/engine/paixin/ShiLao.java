package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class ShiLao {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	/*
	 * 检查是否符合十老牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		if(logger.isDebugEnabled()) {
			logger.debug("checking paixin: shilao," + ",cards=" + PaiXinHelper.toCardsList(handCards));
		}
		
		int count = 0;
		for(MJCard c : handCards) {
			if(!Mahjong.isWanTiaoTong(c.getValue())) continue;
			if(c.getRemain() >= 5) count++;
		}
		
		if(groupList != null) {
			for (CardGroup group : groupList) {
				// if(!group.isValid()) continue;
				for (MJCard c : group.getCards()) {
					if (!Mahjong.isWanTiaoTong(c.getValue()))
						continue;
					if (c.getRemain() >= 5)
						count++;
				}
			}
		}

		return count >= 10;
	}
	
}
