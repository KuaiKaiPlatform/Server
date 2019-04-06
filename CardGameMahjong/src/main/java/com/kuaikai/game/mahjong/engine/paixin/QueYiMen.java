package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;
import com.kuaikai.game.mahjong.msg.pb.CardTypePB.CardType;

public class QueYiMen {
	
	/*
	 * 检查是否符合缺一门牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, almightyCardNum);
		
		// 按牌的类型进行分组，万、条、筒、字、万能牌等
		Map<CardType, List<Integer>> type2Cards = PaiXinHelper.groupCardsByType(cards);	
		int count = 0;	// 万条筒计数
		for(CardType type : type2Cards.keySet()) {
			if(Mahjong.isWanTiaoTong(type)) count++;
		}
		
		return count == 2;	// 万条筒中有两个
	}
	
}
