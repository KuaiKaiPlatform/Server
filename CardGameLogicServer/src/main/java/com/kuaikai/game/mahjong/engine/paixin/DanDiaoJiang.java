package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Set;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class DanDiaoJiang {
	
	/*
	 * 检查是否符合单钓将牌型：如果玩家自摸万能牌，找到玩家听的唯一一张牌，判断是否单钓将。
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum, MahjongPlayer player) {
		if(card == null) return false;
		Set<Integer> tingCards = player.getMjPlayer().getTingCardsWithoutAlmighty();
		if(tingCards == null || tingCards.size() != 1) return false;	// 必须单钓
		
		int diaoJiang = card.getValue();
		boolean ziMoAlmighty = false;
		if(player.equals(card.getPlayer()) && card.getValue() == almightyCardNum) {	// 玩家自摸万能牌
			ziMoAlmighty = true;
			for(int c : tingCards) {
				diaoJiang = c;	// 听的这张牌是单钓将
			}
		}
		
		// 去掉单钓的一对将，看是否能胡牌
		List<Integer> cards = PaiXinHelper.getHandCardsList(handCards, almightyCardNum);
		if(ziMoAlmighty) {
			if(!cards.remove(new Integer(PaiXinHelper.ALMIGHTY_CARD_NUM))) return false;
		} else {
			if(!cards.remove(new Integer(diaoJiang))) return false;
		}
		if(!cards.remove(new Integer(diaoJiang))) return false;
		
		if(cards.contains(diaoJiang)) return false;	// 只能有两张这个将牌，不是2张不算，如：4556，6678
		
		return BiaoZhunHu.checkSentence(cards);
	}
	
}
