package com.kuaikai.game.mahjong.engine.paixin;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class SanLianDui {
	
	/*
	 * 检查是否符合三连对牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard mjCard, List<CardGroup> groupList, int almightyCardNum) {		
		List<Integer> cards = PaiXinHelper.getHandAndChiCards(handCards, mjCard, groupList, almightyCardNum);
		return checkSanLianDui(cards) > 0;
	}
	
	public static int checkSanLianDui(List<Integer> cards) {
		Map<Integer, Integer> card2count = PaiXinHelper.countCards(cards);
		for(int card : card2count.keySet()) {
			if(!Mahjong.isWanTiaoTong(card)) continue;
			if(card2count.get(card) < 2) continue;
			
			if(!card2count.containsKey(card+1)) continue;
			if(card2count.get(card+1) < 2) continue;
			
			if(!card2count.containsKey(card+2)) continue;
			if(card2count.get(card+2) < 2) continue;
			
			List<Integer> cards2check = new LinkedList<Integer>();
			cards2check.addAll(cards);
			
			cards2check.remove(new Integer(card));
			cards2check.remove(new Integer(card));
			
			cards2check.remove(new Integer(card+1));
			cards2check.remove(new Integer(card+1));
			
			cards2check.remove(new Integer(card+2));
			cards2check.remove(new Integer(card+2));
			
			if(BiaoZhunHu.check(cards2check)) return card;
		}
		return 0;		
	}
	
}
