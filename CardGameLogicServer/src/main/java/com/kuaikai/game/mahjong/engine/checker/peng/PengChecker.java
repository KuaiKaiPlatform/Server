package com.kuaikai.game.mahjong.engine.checker.peng;

import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.PengOperation;

/***
 * 碰牌检查器
 */
public interface PengChecker {

	/*
	 * 检查并返回指定玩家的碰牌操作
	 */	
	public PengOperation checkPengOperation(BaseOperation oper);

}
