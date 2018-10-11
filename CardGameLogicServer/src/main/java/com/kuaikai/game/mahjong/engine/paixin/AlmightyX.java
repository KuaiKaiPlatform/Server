package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class AlmightyX {
	
	/*
	 * 检查是否符合4个或3个万能牌牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum, int need, MahjongPlayer player) {
		// 计所有牌，手牌和明牌，万能牌值不转为100
		if(player.getGameDesk().getSetting().getBool(GameSetting.ALMIGHTY_X_COUNT_ALL)) {
			List<Integer> cards = PaiXinHelper.getAllCards(handCards, groupList, -1);
			Map<Integer, Integer> cardCount = PaiXinHelper.countCards(cards);
			Integer countAlmighty = cardCount.get(almightyCardNum);	// 万能牌计数
			return countAlmighty != null && countAlmighty >= need;
		}
		
		Map<Integer, Integer> cardCount = PaiXinHelper.countCards(handCards, almightyCardNum);
		Integer countAlmighty = cardCount.get(PaiXinHelper.ALMIGHTY_CARD_NUM);	// 万能牌计数
		return countAlmighty != null && countAlmighty >= need;
	}
	
}
