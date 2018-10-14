package com.kuaikai.game.mahjong.engine.checker.paixin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class PaiXinChecker {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");

	private Set<Integer> result = new HashSet<Integer>(); // 所有检查到的牌型集合
	
	private SingleChecker checker;		// 牌型检查器
	
	private Map<Object, Object> extra;	// 牌型检查后的附加信息：暗刻、杠数、支数、通数等。	
	
	private MahjongPlayer player; 
	
	public PaiXinChecker(MahjongPlayer player) {
		this.player = player;
	}

	public MahjongPlayer getPlayer() {
		return player;
	}

	public void setChecker(SingleChecker checker) {
		this.checker = checker;
	}

	public Map<Object, Object> getExtra() {
		return extra;
	}

	public Object getExtra(Object key) {
		if(extra == null) return null;
		return extra.get(key);
	}
	
	public void putExtra(Object key, Object value) {
		if(extra == null) extra = new HashMap<Object, Object>();
		extra.put(key, value);
	}
	
	public Set<Integer> getResult() {
		return result;
	}
	
	public void addResult(int paiXin) {
		result.add(paiXin);
	}
	
	public boolean containsResult(int paiXin) {
		return result.contains(paiXin);
	}

	/*
	 * 已检查牌型中是否包含任一指定牌型
	 */
	public boolean containsAnyResult(Set<Integer> paiXins) {
		if(paiXins.isEmpty()) return true;
		if(paiXins.contains(PaiXin.QI_DUI)) {
			if(containsQiDui()) return true;
		}
		for(int paiXin : paiXins) {
			if(result.contains(paiXin)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 已检查牌型中是否包含所有指定牌型
	 */
	public boolean containsAllResult(Set<Integer> paiXins) {
		if(paiXins.isEmpty()) return true;
		if(paiXins.contains(PaiXin.QI_DUI)) {
			if(!containsQiDui()) return false;
			paiXins.remove(PaiXin.QI_DUI);
		}
		return result.containsAll(paiXins);
	}
	
	public void clearResult() {
		result.clear();
		if(extra != null) extra.clear();
	}
	
	public boolean check(MahjongPlayer player, List<MJCard> handlist, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		// 检查checker能否胡牌。
		if(checker != null) {
			checker.check(player, handlist, card, groupList, almightyCardNum);
		}
		return !result.isEmpty();
	}
	
	public boolean containsQiDui() {
		return containsQiDui(result);
	}
	
	public static boolean containsQiDui(Set<Integer> paiXins) {
		return paiXins.contains(PaiXin.QI_DUI) || paiXins.contains(PaiXin.HAO_QI_DUI) || paiXins.contains(PaiXin.CHAO_HAO_QI_DUI) || paiXins.contains(PaiXin.ZUI_HAO_QI_DUI);
	}
	
}
