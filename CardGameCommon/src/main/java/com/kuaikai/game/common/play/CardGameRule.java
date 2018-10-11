package com.kuaikai.game.common.play;

import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;

public class CardGameRule {
	
	private static final int MAJONG_POKER_SEPARATOR = 99000;
	
	/**
	 * 检查是否麻将玩法
	 */
	public static boolean isMahjong(GameRule rule) {
		return rule.getNumber() < MAJONG_POKER_SEPARATOR;
	}

	/**
	 * 检查是否扑克玩法
	 */
	public static boolean isPoker(GameRule rule) {
		return rule.getNumber() > MAJONG_POKER_SEPARATOR;
	}
	
}
