package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Set;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class KanZhang {
	
	/*
	 * 检查是否符合坎张牌型：如果玩家自摸万能牌，找到玩家听的唯一一张牌，判断是否坎张。
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum, MahjongPlayer player) {
		if(card == null) return false;
		Set<Integer> tingCards = player.getMjPlayer().getTingCardsWithoutAlmighty();
		if(tingCards == null || tingCards.size() != 1) return false;	// 必须单钓
		
		int kanZhang = card.getValue();
		boolean ziMoAlmighty = false;
		if(player.equals(card.getPlayer()) && card.getValue() == almightyCardNum) {	// 玩家自摸万能牌
			ziMoAlmighty = true;
			for(int c : tingCards) {
				kanZhang = c;	// 听的这张牌是坎张的牌
			}
		}
		
		if(!Mahjong.isWanTiaoTong(kanZhang)) return false;
		
		// 去掉坎张的一顺，看是否能胡牌
		List<Integer> cards = PaiXinHelper.getHandCardsList(handCards, almightyCardNum);
		
		if(ziMoAlmighty) {
			if(!cards.remove(new Integer(PaiXinHelper.ALMIGHTY_CARD_NUM))) return false;	
		} else {
			if(!cards.remove(new Integer(kanZhang))) return false;	
		}
		
		if(!cards.remove(new Integer(kanZhang-1))) return false;
		if(!cards.remove(new Integer(kanZhang+1))) return false;
		
		return BiaoZhunHu.check(cards);
	}
	
}
