package com.kuaikai.game.mahjong.engine.paixin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class AnKe {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	/*
	 * 检查本副牌的暗刻集合，只有去掉三张同样的手牌还能胡牌才算暗刻
	 * 
	 */
	public static Set<Integer> check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum, MahjongPlayer player) {
		Set<Integer> result = new HashSet<Integer>();
		List<Integer> cards = new ArrayList<Integer>();
		for(MJCard hand : handCards) {	// 所有手牌
			cards.add(hand.getValue());
		}
		Collections.sort(cards);	// 排序
		
		boolean moCard = player.equals(card.getPlayer());
		Map<Integer, Integer> card2Count = PaiXinHelper.countCards(handCards, almightyCardNum);
		for(Map.Entry<Integer, Integer> entry : card2Count.entrySet()) {
			int count = entry.getValue();
			if(count < 3) continue;
			int cardNum = entry.getKey();
			
			// 别人打的牌，必须有四张才算暗刻
			if(card != null && cardNum == card.getValue() && !moCard && count < 4) continue;
			
			// 自己摸的牌，当摸牌不算暗刻时，必须有四张才算暗刻
			if(card != null && cardNum == card.getValue() && moCard && player.getRoom().getCreateRoomParam().getSettingBool(GameSetting.MO_NOT_AN_KE) && count < 4) continue;
			
			for(int i=0; i<3; i++) {
				cards.remove(new Integer(cardNum));	// 删除三张
			}

			//if(logger.isDebugEnabled()) logger.debug("checking paixin:biaozhunhu,cards=" + cards);
			if(!BiaoZhunHu.check(cards)) {	// 胡不了，还原，继续检查其他牌
				for(int i=0; i<3; i++) {
					cards.add(cardNum);	
				}
				Collections.sort(cards);	// 排序
				continue;
			}
			// 移除刻子牌后仍能胡牌，记录刻子
			result.add(cardNum);
		}
		return result;
	}
	
}
