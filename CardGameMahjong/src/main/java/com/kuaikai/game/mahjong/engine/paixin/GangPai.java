package com.kuaikai.game.mahjong.engine.paixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class GangPai {
	
	/*
	 * 返回指定牌面杠牌的记录
	 */
	public static Map<Integer, Integer> check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		
		if(groupList == null) return result;
		
		for(CardGroup group : groupList) {
			//if(!group.isValid()) continue;
			switch(group.getOperType()) {
			case OperType.AN_GANG :
			case OperType.BU_GANG :
			case OperType.DIAN_GANG :
				result.put(group.getCards().get(0).getValue(), group.getOperType());
				break;
			}
		}
		
		return result;
	}
	
}
