package com.kuaikai.game.mahjong.engine.checker.paixin;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class CheckerArray extends SingleChecker {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");

	private List<SingleChecker> checkers = new LinkedList<SingleChecker>();	// 牌型检查器列表
	
	public void addSingleChecker(SingleChecker checker) {
		checkers.add(checker);
	}
	
	/*
	 * 依次检查 checkers 里所有的 checker 能否胡牌，任何一个能胡牌就返回 true。
	 * 
	 */
	@Override
	public boolean check(MahjongPlayer player, List<MJCard> handlist, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		boolean checked = false;
		for(SingleChecker checker : checkers) {
			checked = checker.check(player, handlist, card, groupList, almightyCardNum) || checked;
		}
		
		if(checked) return true;
		checkNext(player, handlist, card, groupList, almightyCardNum);	// 本checker不能胡，则检查nextChecker能否胡牌，或nextCheckerArr能否胡牌。
		return checked;
	}
	
}
