package com.kuaikai.game.mahjong.engine.checker.ting;

import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.TingOperation;

/**
 * 听牌检查器
 */
public interface TingChecker {

	/*
	 * 检查并返回指定玩家的听牌操作
	 */	
	public TingOperation checkTingOperation(BaseOperation oper);

}
