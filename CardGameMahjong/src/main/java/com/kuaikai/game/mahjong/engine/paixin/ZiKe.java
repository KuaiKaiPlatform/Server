package com.kuaikai.game.mahjong.engine.paixin;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class ZiKe {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	/*
	 * 根据extra中的明刻、暗刻和杠牌检查字刻牌型，返回相应的牌型集合：四字刻、三字刻、三字刻附将、大三元、大四喜等
	 */
	public static Set<Integer> check(Map extra, List<MJCard> handCards) {
		Set<Integer> paiXins = new HashSet<Integer>();
		if(extra == null) return paiXins;
		
		int[] counts = {0, 0};	// 数组第一个是风刻数，第二个是箭刻数
		
		// 检查杠牌
		Map<Integer, Integer> gangPai = (Map<Integer, Integer>)extra.get(PaiXin.GANG_PAI);
		if(gangPai != null && !gangPai.isEmpty()) {
			countZi(gangPai.keySet(), counts);
		}
		
		// 检查明刻
		Set<Integer> mingKe = (Set<Integer>)extra.get(PaiXin.MING_KE);
		if(mingKe != null && !mingKe.isEmpty()) {
			countZi(mingKe, counts);
		}

		// 检查暗刻
		Set<Integer> anKe = (Set<Integer>)extra.get(PaiXin.AN_KE);
		if(anKe != null && !anKe.isEmpty()) {
			countZi(anKe, counts);
		}
		
		// 四字刻、三字刻
		int total = counts[0] + counts[1];
		switch(total) {
		case 4 :
			paiXins.add(PaiXin.SI_ZI_KE);
			break;
		case 3 :
			paiXins.add(PaiXin.SAN_ZI_KE);
			// 检查是否附将
			if(checkFuJiang(handCards)) {
				paiXins.add(PaiXin.SAN_ZI_KE_FU_JIANG);
			}
			break;
		}
		
		// 大四喜
		if(counts[0] == 4) {
			paiXins.add(PaiXin.DA_SI_XI);
		}
		
		// 大三元
		if(counts[1] == 3) {
			paiXins.add(PaiXin.DA_SAN_YUAN);
		}
		
		return paiXins;
	}
	
	/**
	 * 数组第一个是风刻数，第二个是箭刻数
	 */
	private static void countZi(Collection<Integer> cards, int[] counts) {
		for(int card : cards) {
			if(Mahjong.isFeng(card)) {
				counts[0]++;	
			} else if(Mahjong.isJian(card)) {
				counts[1]++;
			}
		}
	}
	
	private static boolean checkFuJiang(List<MJCard> handCards) {
		List<Integer> cards = PaiXinHelper.toCardsList(handCards);
		Map<Integer, Set<Integer>> count2Cards = PaiXinHelper.groupCardsByCount(cards);
		Set<Integer> cards2 = count2Cards.get(2);
		if(cards2 == null) return false;
		for(int card : cards2) {
			if(Mahjong.isZi(card)) return true;
		}
		return false;
	}
	
}
