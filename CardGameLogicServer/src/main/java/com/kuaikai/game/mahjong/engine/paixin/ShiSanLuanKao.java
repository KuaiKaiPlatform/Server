package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class ShiSanLuanKao {
	
	/*
	 * 检查是否符合十三乱靠牌型（十三乱靠中万条筒可重复，并且牌值之间相差3或3以上均可，如：148万，259条，369筒），返回值为字牌的数量：7为七星乱靠，5为五星乱靠，0 不是十三乱靠牌型
	 */
	public static int check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		if(groupList != null && groupList.size() > 0) return 0;
		int size = handCards.size();
		if(size != 14) return 0;

		List<Integer> cards = PaiXinHelper.getHandCardsList(handCards, almightyCardNum);
		return check(cards);
	}
	
	/*
	 * 检查一个有序的麻将牌列表是否满足十三乱靠牌型，返回值为字牌的数量：7为七星乱靠，5为五星乱靠，0 不是十三乱靠牌型
	 */
	public static int check(List<Integer> cards) {
		Map<Mahjong.CardType, List<Integer>> type2Cards = PaiXinHelper.groupCardsByType(cards);
		
		// 万能牌数量
		List<Integer> cardsAlmighty = type2Cards.remove(Mahjong.CardType.ALMIGHTY);
		int countAlmighty = (cardsAlmighty!=null)?cardsAlmighty.size():0;
		
		// 字牌数量
		if(!type2Cards.containsKey(Mahjong.CardType.ZI)) return 0;
		List<Integer> cardsZi = type2Cards.remove(Mahjong.CardType.ZI);
		int countZi = cardsZi.size();
		if((countZi+countAlmighty) < 5) return 0;	// 字牌加万能牌数量小于5，不是十三乱靠
		if(PaiXinHelper.hasSameCard(cardsZi)) return 0;	// 有重复字牌，不是十三乱靠
		
		// 检查万条筒是否符合十三乱靠牌型要求
		for(Map.Entry<Mahjong.CardType, List<Integer>> entry : type2Cards.entrySet()) {
			List<Integer> cardsX = entry.getValue();
			if(cardsX.size() > 3) return 0;	// 同花色不能超过3张
			
			// 牌值差为3或3以上
			int last = cardsX.get(0);
			for(int i=1; i<cardsX.size(); i++) {
				int current = cardsX.get(i);
				int delta = current - last;
				if(delta < 3) return 0;
				last = current;
			}
		}
		
		return Math.min(7, (countZi+countAlmighty));
	}
	
}
