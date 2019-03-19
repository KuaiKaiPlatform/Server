package com.kuaikai.game.mahjong.engine.paixin;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.Mahjong;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

public class YiSeYaoJiuKe {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	/*
	 * 根据extra中的明刻、暗刻和杠牌检查是否符合一色幺九刻牌型，返回值为一色幺九刻的数量
	 */
	public static Set<Integer> check(Map extra) {
		Set<Integer> paiXins = new HashSet<Integer>();
		if(extra == null) return paiXins;
		
		int count = 0;
		Map<Mahjong.CardType, Integer> type2count = new HashMap<Mahjong.CardType, Integer>();
		
		// 检查杠牌
		Map<Integer, Integer> gangPai = (Map<Integer, Integer>)extra.get(JieSuan.GANG_PAI_VALUE);
		if(gangPai != null && !gangPai.isEmpty()) {
			checkAndCountType(gangPai.keySet(), type2count);
		}
		
		// 检查明刻
		Set<Integer> mingKe = (Set<Integer>)extra.get(JieSuan.MING_KE_VALUE);
		if(mingKe != null && !mingKe.isEmpty()) {
			checkAndCountType(mingKe, type2count);
		}

		// 检查暗刻
		Set<Integer> anKe = (Set<Integer>)extra.get(JieSuan.AN_KE_VALUE);
		if(anKe != null && !anKe.isEmpty()) {
			checkAndCountType(anKe, type2count);
		}
		
		for(int typeCount : type2count.values()) {
			if(typeCount == 2) count++;
		}
		
		switch(count) {
		case 1 :
			paiXins.add(JieSuan.YI_SE_YAO_JIU_KE_VALUE);
			break;
		case 2 :
			paiXins.add(JieSuan.YI_SE_YAO_JIU_KE_SHUANG_VALUE);
			break;
		}
		
		return paiXins;
	}
	
	private static void checkAndCountType(Collection<Integer> cards, Map<Mahjong.CardType, Integer> type2count) {
		for(int card : cards) {
			if(!Mahjong.isYaoJiu(card)) continue;
			Mahjong.CardType type = Mahjong.getCardType(card);
			if(type2count.containsKey(type)) {
				type2count.put(type, type2count.get(type)+1);
			} else {
				type2count.put(type, 1);
			}
		}		
	}
	
}
