package com.kuaikai.game.mahjong.engine.paixin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class BiaoZhunHu {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	public static void main(String args[]) {
		
/*		List<Integer> cardList = Arrays.asList(17,18,19,31,31,32,33,34,36,37,38,39,39,100);
		System.out.println(check(cardList));

		cardList = Arrays.asList(15,15,17,17,17,21,22,23,24,26,27,28,29,100);
		System.out.println(check(cardList));
		
		cardList = Arrays.asList(14,15,21,22,23,24,26,27,28,29,32,32,100,100);
		System.out.println(check(cardList));

		cardList = Arrays.asList(11,11,11,12,13,14,15,17,26,26,100);
		System.out.println(check(cardList));
		
		cardList = Arrays.asList(24,25,26,34,34,35,36,37);
		System.out.println(check(cardList));

		cardList = Arrays.asList(13,13,13,15,16,17,18,18,18,45,45);
		System.out.println(check(cardList));

		cardList = Arrays.asList(11,11,14,15,17,17,100,100);
		System.out.println(check(cardList));
		
		cardList = Arrays.asList(13,13,13,14,15,15,16,16,17,18,19);
		System.out.println(check(cardList));*/
		
/*		List<Integer> cardList = Arrays.asList(13,14,33,35,38,100,100,100);
		System.out.println(check(cardList));*/

		//List<Integer> cardList = Arrays.asList(22,23,23,25,27,28,32,32,32,35,35,100,100,100);
		//System.out.println(check(cardList));
		
		List<Integer> cardList = Arrays.asList(23,24,28,31,32,100,100,100);
		System.out.println(check(cardList));		
		
/*		int[] cards = {31,31,32,33,34,36};
		int[] result = new int[3];
		pickArray(cards, 0, 3, result, 3);*/
		
	}

	public static void pickArray(int[] arr, int start, int count, int[] result, int n) {
		for(int i=start; i<arr.length+1-count; i++) {
			result[count-1] = i;
			if((count-1) == 0) {
				for(int j=n-1; j>=0; j--) {
					System.out.print(arr[result[j]] + ",");
				}
				System.out.println();
			} else {
				pickArray(arr, i+1, count-1, result, n);
			}
		}
	}
	
	/*
	 * 检查是否符合标准胡牌型
	 */
	public static boolean check(List<MJCard> handCards, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		if(handCards.size()%3 != 2) return false;
		List<Integer> cards = PaiXinHelper.getHandCardsList(handCards, almightyCardNum);
		return check(cards);
	}
	
	/*
	 * 检查一个有序的麻将牌列表是否可胡牌
	 */
	public static boolean check(List<Integer> cards) {
		// 去掉对子，看余下的牌能否组成顺子或刻子
		
		Map<Integer, Set<Integer>> count2Cards = PaiXinHelper.groupCardsByCount(cards); // 按牌的张数进行分组：key 是几张牌：1-4，value是这个张数的牌集合。
		
/*		for(Map.Entry<Integer, Set<Integer>> entry : count2Cards.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}*/
		
		// 去掉刚好2张的对子
		if(count2Cards.containsKey(2)) {
			for(Integer card : count2Cards.get(2)) {
				if(quDuiAndCheck(cards, card, false)) return true;
			}
		}
		
		// 去掉3张牌中的两张作为对子
		if(count2Cards.containsKey(3)) {
			for(Integer card : count2Cards.get(3)) {
				if(quDuiAndCheck(cards, card, false)) return true;
			}
		}
		
		// 去掉4张牌中的两张作为对子
		if(count2Cards.containsKey(4)) {
			for(Integer card : count2Cards.get(4)) {
				if(quDuiAndCheck(cards, card, false)) return true;
			}
		}
		
		// 去掉1张牌加一个万能牌作为对子
		if(count2Cards.containsKey(1) && cards.contains(PaiXinHelper.ALMIGHTY_CARD_NUM)) {
			for(Integer card : count2Cards.get(1)) {
				if(card == PaiXinHelper.ALMIGHTY_CARD_NUM) continue;
				if(quDuiAndCheck(cards, card, true)) return true;
			}
		}		
		
		return false;
	}
	
	/*
	 * 去掉指定牌的两张后检查剩余的牌能否组成顺子或刻子；当去万能牌时，去掉万能牌和指定牌的一张
	 */
	public static boolean quDuiAndCheck(List<Integer> cards, int card, boolean quAlmighty) {
		List<Integer> cardsQuDui = new ArrayList<Integer>();
		cardsQuDui.addAll(cards);
		cardsQuDui.remove(new Integer(card));
		if(quAlmighty)
			cardsQuDui.remove(new Integer(PaiXinHelper.ALMIGHTY_CARD_NUM));
		else
			cardsQuDui.remove(new Integer(card));
		return checkSentence(cardsQuDui);
	}
	
	/*
	 * 检查一个有序的麻将牌列表能否组成顺子或刻子
	 */
	public static boolean checkSentence(List<Integer> cards) {
		if(cards.isEmpty()) return true;
		//System.out.println("checking sentence, cards=" + cards);
		
		Map<Mahjong.CardType, List<Integer>> type2Cards = PaiXinHelper.groupCardsByType(cards);	// 按牌的类型进行分组，万、条、筒、风、万能牌等
		List<Integer> almightyCards = type2Cards.remove(Mahjong.CardType.ALMIGHTY);
		int countAlmighty = (almightyCards!=null)?almightyCards.size():0;	// 万能牌数量
		
		// 用万能牌补充各类型的牌到3的倍数
		for(List<Integer> cardsOneType : type2Cards.values()) {
			int remain = cardsOneType.size() % 3;
			if(remain == 0) continue;
			for(int i=3; i>remain; i--) {
				if(--countAlmighty < 0) return false;	// 万能牌不够补充，不能组成顺子或刻子
				cardsOneType.add(PaiXinHelper.ALMIGHTY_CARD_NUM);
			}
		}
		
		// 万能牌都补完
		if(countAlmighty == 0) {	
			// 按每个类型的牌数排序：例如，有三张万，六张筒，就先检查万
			List<List<Integer>> sortedCards = new LinkedList<List<Integer>>();
			sortedCards.addAll(type2Cards.values());
			Collections.sort(sortedCards, new Comparator<List<Integer>>() {

				@Override
				public int compare(List<Integer> cards1, List<Integer> cards2) {
					return cards1.size()-cards2.size();
				}
				
			});
			
			// 每个类型的牌都要能组成顺子或刻子
			for(List<Integer> cardsOneType : sortedCards) {
				if(!checkOneType(cardsOneType)) return false;
			}
			return true;
		}
		
		// 剩三张万能牌，找到单个无法组成顺子或刻子的牌型，补充万能牌，再试
		int countUnchecked = 0;
		List<Integer> cardsUnchecked = null;
		for(List<Integer> cardsOneType : type2Cards.values()) {
			if(!checkOneType(cardsOneType)) {
				cardsUnchecked = cardsOneType;
				countUnchecked++;
			}
		}

		if(countUnchecked > 1)	// 多于一个无法组成顺子或刻子的牌型
			return false;
		else if(countUnchecked == 0)	// 不需要万能牌都能组成顺子或刻子
			return true;
		
		// 补充万能牌，再试
		for(int i=0; i<countAlmighty; i++) cardsUnchecked.add(PaiXinHelper.ALMIGHTY_CARD_NUM);
		return checkOneType(cardsUnchecked);
	}
	
	/*
	 * 检查一个有序的同类型麻将牌列表能否组成顺子或刻子。
	 */
	public static boolean checkOneType(List<Integer> cardsOneType) {
		//System.out.println("checking one type, cards=" + cardsOneType);
		if(cardsOneType.size() == 3) return check3(cardsOneType);
		return checkMore(cardsOneType);
	}
	
	/*
	 * 检查一个多位有序的同类型麻将牌列表能否组成顺子或刻子。
	 */
	public static boolean checkMore(List<Integer> cards) {
		int[] arr = new int[cards.size()];
		int i=0;
		for(int card : cards) {
			arr[i++] = card;
		}
		int[] result = new int[3];
		return checkArray(cards, arr, 0, 3, result, 3);
	}
	
	/*
	 * 从 arr 中的元素中取出 3个元素，排列组合后检查能否组成顺子或刻子
	 */
	public static boolean checkArray(List<Integer> cards, int[] arr, int start, int count, int[] result, int n) {
		for(int i=start; i<arr.length+1-count; i++) {
			result[count-1] = i;
			if((count-1) == 0) {	// 取出三张牌
				List<Integer> card3 = new ArrayList<Integer>();
				for(int j=n-1; j>=0; j--) {
					card3.add(arr[result[j]]);
				}
				if(check3(card3)) {	// 这三张牌能组成顺子或刻子，检查剩余的牌
					List<Integer> remain = new ArrayList<Integer>();
					remain.addAll(cards);
					for(int card : card3) {
						remain.remove(new Integer(card));	
					}
					if(checkOneType(remain)) return true; 
				}
			} else {
				if(checkArray(cards, arr, i+1, count-1, result, n)) return true;
			}
		}
		return false;
	}
	
	/*
	 * 检查一个3位有序的同类型麻将牌列表能否组成顺子或刻子。
	 */
	public static boolean check3(List<Integer> cards) {
		//System.out.println("checking 3, cards=" + cards);
		return isShunZi(cards) || isKeZi(cards);
	}
	
	/*
	 * 检查一个3位有序的同类型麻将牌列表能否组成顺子。
	 */
	public static boolean isShunZi(List<Integer> cards) {
		if(!PaiXinHelper.isWanTiaoTong(cards)) return false;
		int card1 = cards.get(0);
		int card2 = cards.get(1);
		int card3 = cards.get(2);
		if(card1+1 == card2 && card2+1 == card3) return true;	// 真的一顺子
		if(PaiXinHelper.ALMIGHTY_CARD_NUM == card2 && PaiXinHelper.ALMIGHTY_CARD_NUM == card3) return true;	// 2个及以上万能牌
		if((card2-card1) <= 2 && card3 == PaiXinHelper.ALMIGHTY_CARD_NUM) return true;	// 1个万能牌
		return false;
	}

	/*
	 * 检查一个3位有序的同类型麻将牌列表能否组成刻子。
	 */
	public static boolean isKeZi(List<Integer> cards) {
		int num = cards.get(0);
		for(int i=1; i<cards.size(); i++) {
			int card = cards.get(i);
			if(card == PaiXinHelper.ALMIGHTY_CARD_NUM) continue; // 万能牌，略过
			if(card != num) return false;
		}
		return true;
	}
	
}
