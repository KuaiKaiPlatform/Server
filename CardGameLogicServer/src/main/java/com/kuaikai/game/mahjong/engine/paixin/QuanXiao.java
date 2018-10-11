package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class QuanXiao {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	/*
	 * 检查是否符合全小牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		if(logger.isDebugEnabled()) {
			logger.debug("checking paixin: quanxiao," + ",cards=" + PaiXinHelper.toCardsList(handCards));
		}
		
		for(MJCard c : handCards) {
			if(!Mahjong.isWanTiaoTong(c.getValue())) return false;
			if(c.getRemain() > 5) return false;
		}
		
		if(groupList == null) return true;
		
		for(CardGroup group : groupList) {
			//if(!group.isValid()) continue;
			for(MJCard c : group.getCards()) {
				if(!Mahjong.isWanTiaoTong(c.getValue())) return false;
				if(c.getRemain() > 5) return false;		
			}
		}
		return true;
	}
	
}
