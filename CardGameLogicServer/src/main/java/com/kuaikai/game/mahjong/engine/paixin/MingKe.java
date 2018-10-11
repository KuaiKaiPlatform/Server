package com.kuaikai.game.mahjong.engine.paixin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class MingKe {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	/*
	 * 检查本副牌的明刻集合：去掉三张同样的胡牌还能胡也算明刻
	 * 
	 */
	public static Set<Integer> check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum, MahjongPlayer player) {
		Set<Integer> result = new HashSet<Integer>();
		// 加入碰牌
		if(groupList != null) {
			for (CardGroup group : groupList) {
				// if(!group.isValid()) continue;
				if (!group.checkOperType(OperType.PENG))
					continue;
				result.add(group.getCards().get(0).getValue());
			}
		}
		
		if(card == null) return result;
		
		// 自摸算明刻时继续检查
		if(player.equals(card.getPlayer()) && !player.getGameDesk().getSetting().getBool(GameSetting.MO_MING_KE)) {
			return result;
		}
		
		int count = 0;
		List<Integer> cards = new ArrayList<Integer>();
		for(MJCard hand : handCards) {	// 所有手牌
			if(hand.getValue() == card.getValue()) count++;
			cards.add(hand.getValue());
		}
		if(count != 3) return result;	// 3张算明刻，4张算暗刻
		Collections.sort(cards);	// 排序

		for(int i=0; i<3; i++) {
			cards.remove(new Integer(card.getValue()));	// 删除三张
		}

		//if(logger.isDebugEnabled()) logger.debug("checking paixin:biaozhunhu,cards=" + cards);
		if(BiaoZhunHu.check(cards)) {
			result.add(card.getValue());
		}
		
		return result;
	}
	
}
