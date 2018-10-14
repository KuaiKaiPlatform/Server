package com.kuaikai.game.mahjong.engine.checker.chi;

import java.util.List;

import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;

/***
 * 吃牌检查器
 * 
 */
public interface ChiChecker {

	/*
	 * 检查并返回指定玩家的吃牌操作
	 */	
	public List<BaseOperation> checkChiOperations(BaseOperation oper);
	
	/*
	 * 指定DaAction，返回玩家可吃的顺子组合
	 */
	public List<List<MJCard>> checkShunZis(BaseOperation oper);

}
