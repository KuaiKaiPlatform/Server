package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Set;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class BianZhang {
	
	/*
	 * 检查是否符合边张牌型：如果玩家自摸万能牌，找到玩家听的唯一一张牌，判断是否边张。
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum, MahjongPlayer player) {
		if(card == null) return false;
		Set<Integer> tingCards = player.getMjPlayer().getTingCardsWithoutAlmighty();
		if(tingCards == null || tingCards.size() != 1) return false;	// 必须单钓
		
		int bianZhang = card.getValue();
		boolean ziMoAlmighty = false;
		if(player.equals(card.getPlayer()) && card.getValue() == almightyCardNum) {	// 玩家自摸万能牌
			ziMoAlmighty = true;
			for(int c : tingCards) {
				bianZhang = c;	// 听的这张牌是边张的牌
			}
		}
		
		if(!Mahjong.isWanTiaoTong(bianZhang)) return false;
		
		int remain = Mahjong.getRemain(bianZhang);
		if(remain != 3 && remain != 7) return false;
		
		// 去掉边张的一顺，看是否能胡牌
		List<Integer> cards = PaiXinHelper.getHandCardsList(handCards, almightyCardNum);
		
		if(ziMoAlmighty) {
			if(!cards.remove(new Integer(PaiXinHelper.ALMIGHTY_CARD_NUM))) return false;	
		} else {
			if(!cards.remove(new Integer(bianZhang))) return false;	
		}
		
		if(remain == 3) {
			if(!cards.remove(new Integer(bianZhang-1))) return false;
			if(!cards.remove(new Integer(bianZhang-2))) return false;			
		} else if(remain == 7) {
			if(!cards.remove(new Integer(bianZhang+1))) return false;
			if(!cards.remove(new Integer(bianZhang+2))) return false;
		}
		
		return BiaoZhunHu.check(cards);
	}
	
}
