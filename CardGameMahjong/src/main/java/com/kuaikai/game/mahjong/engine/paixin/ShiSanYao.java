package com.kuaikai.game.mahjong.engine.paixin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;


public class ShiSanYao {
	
	private static final Set<Integer> shiSanYaoCardNums = new HashSet<Integer>();
	
	static {
		shiSanYaoCardNums.add(11);
		shiSanYaoCardNums.add(19);
		shiSanYaoCardNums.add(21);
		shiSanYaoCardNums.add(29);
		shiSanYaoCardNums.add(31);
		shiSanYaoCardNums.add(39);
		shiSanYaoCardNums.add(41);
		shiSanYaoCardNums.add(42);
		shiSanYaoCardNums.add(43);
		shiSanYaoCardNums.add(44);
		shiSanYaoCardNums.add(45);
		shiSanYaoCardNums.add(46);
		shiSanYaoCardNums.add(47);
	}
	
	/*
	 * 检查是否符合十三幺牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		if(groupList != null && groupList.size() > 0) return false;	// 有明牌
		if(handCards.size() != 14) return false;

		Set<Integer> cardNums = new HashSet<Integer>();
		int countAlmighty = 0;
		for (int i = 0; i < handCards.size(); i++) {
			int cardNum = handCards.get(i).getValue();
			if(cardNum == almightyCardNum) {
				countAlmighty++;
				continue;
			}
			if(!shiSanYaoCardNums.contains(cardNum)) return false;	// 不是十三幺牌
			cardNums.add(cardNum);
		}

		if((cardNums.size() + countAlmighty) < 13) {	// 十三幺不全
			return false;
		}

		return true;
	}
	
}
