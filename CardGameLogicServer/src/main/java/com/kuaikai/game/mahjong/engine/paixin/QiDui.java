package com.kuaikai.game.mahjong.engine.paixin;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;

public class QiDui {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	public static void main(String args[]) {
		int[] cards = {12,12,13,13,14,15,15,16,16,18,18,19,19,45};
		System.out.println(check(cards, 45));
		
		int[] cards2 = {11,11,12,12,13,14,14,15,15,16,19,45,45,45};
		System.out.println(check(cards2, 45));
	}
	
	public static int check(int[] handCards, int almightyCardNum) {
		Map<Integer, Integer> cardCount = PaiXinHelper.countCards(handCards, almightyCardNum);
		return check(cardCount);
	}
	
	/*
	 * 检查是否符合七对牌型，返回值正数时为四张牌的数量：-1 不是七对，0 普通七对，1 豪华七对，2 超豪华七对，3 最豪华七对
	 */
	public static int check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		if(groupList != null && groupList.size() > 0) return -1;
		Map<Integer, Integer> cardCount = PaiXinHelper.countCards(handCards, almightyCardNum);
		return check(cardCount);
	}
	
	/*
	 * 检查是否符合七对牌型，返回值正数时为四张牌的数量：-1 不是七对，0 普通七对，1 豪华七对，2 超豪华七对，3 最豪华七对
	 */
	public static int check(Map<Integer, Integer> cardCount) {
		int count4 = 0;			// 四张牌计数
		int count3 = 0;			// 三张牌计数
		int odd = 0;			// 奇数牌计数
		int countAlmighty = 0;	// 万能牌计数
		for(Map.Entry<Integer, Integer> entry : cardCount.entrySet()) {
			int cardNum = entry.getKey();
			int count = entry.getValue();
			if(cardNum == PaiXinHelper.ALMIGHTY_CARD_NUM) {
				countAlmighty = count;
				continue;
			}
			switch(count) {
			case 1 :
				odd++;
				break;
			case 3 :
				odd++;
				count3++;
				break;
			case 4 :
				if(logger.isDebugEnabled()) logger.debug(String.format("QiDui.check@count4,card=%d", cardNum));
				count4++;
				break;
			}
		}
		
		if(countAlmighty < odd) return -1;		// 不够补充奇数牌
		int countBu = (countAlmighty-odd)/2;	// 多出的万能牌，每两张可以补足一组四张牌
		
		if(logger.isDebugEnabled()) logger.debug("count4=" + count4 + ",count3=" + count3 + ",countBu=" + countBu);
		return (count4+count3+countBu);			// 返回四张牌数量
	}
	
}
