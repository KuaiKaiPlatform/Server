package com.kuaikai.game.mahjong.engine.paixin;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

public class LianKe {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	/*
	 * 根据extra中的明刻、暗刻和杠牌检查连刻牌型，返回相应的牌型集合：一色四连刻、一色三连刻附一将、一色三连刻等
	 */
	public static Set<Integer> check(Map extra, List<MJCard> handCards) {
		Set<Integer> paiXins = new HashSet<Integer>();
		if(extra == null) return paiXins;
		
		List<Integer> cards = new LinkedList<Integer>();	// 所有刻牌和杠牌放到一个列表中
		
		// 杠牌
		Map<Integer, Integer> gangPai = (Map<Integer, Integer>)extra.get(PaiXin.GANG_PAI);
		if(gangPai != null && !gangPai.isEmpty()) {
			cards.addAll(gangPai.keySet());
		}
		
		// 明刻
		Set<Integer> mingKe = (Set<Integer>)extra.get(PaiXin.MING_KE);
		if(mingKe != null && !mingKe.isEmpty()) {
			cards.addAll(mingKe);
		}
		
		// 暗刻
		Set<Integer> anKe = (Set<Integer>)extra.get(PaiXin.AN_KE);
		if(anKe != null && !anKe.isEmpty()) {
			cards.addAll(anKe);
		}
		
		Collections.sort(cards);
		
		int count = 0, lianKe = 0;
		int previous = 0, last3 = 0;
		for(int i=0; i<cards.size(); i++) {
			int card = cards.get(i);
			if(Mahjong.isZi(card)) break;
			if(previous == 0) {
				previous = card;
				count++;
				continue;
			}
			if(card==(previous+1)) {
				count++;
				if(count == 3) last3 = card;	// 三连刻的最后一张牌
			} else {
				count = 1;
			}
			lianKe = Math.max(lianKe, count);
			previous = card;
		}
		
		// 一色四连刻、一色三连刻
		switch(lianKe) {
		case 4 :
			paiXins.add(PaiXin.YI_SE_SI_LIAN_KE);
			break;
		case 3 :
			paiXins.add(PaiXin.YI_SE_SAN_LIAN_KE);
			// 检查是否附将
			if(checkFuJiang(handCards, last3+1, last3-3)) {
				paiXins.add(PaiXin.YI_SE_SAN_LIAN_KE_FU_JIANG);
			}
			break;
		}
		
		return paiXins;
	}
	
	private static boolean checkFuJiang(List<MJCard> handCards, int jiang1, int jiang2) {
		Map<Integer, Integer> card2count = PaiXinHelper.countCards(handCards, -1);
		int count1 = card2count.containsKey(jiang1)?card2count.get(jiang1):0;
		int count2 = card2count.containsKey(jiang2)?card2count.get(jiang2):0;
		return count1 >= 2 || count2 >=2;
	}
	
}
