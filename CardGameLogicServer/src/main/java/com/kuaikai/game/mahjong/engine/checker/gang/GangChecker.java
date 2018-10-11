package com.kuaikai.game.mahjong.engine.checker.gang;

import java.util.List;

import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.DianGangOperation;

/***
 * 杠牌检查器
 * 
 */
public interface GangChecker {

	/*
	 * 检查并返回指定玩家的补杠操作（摸、吃、碰后）
	 */	
	public List<BaseOperation> checkBuGangOperations(BaseOperation oper);

	/*
	 * 检查并返回指定玩家的暗杠操作（摸、吃、碰后）
	 */	
	public List<BaseOperation> checkAnGangOperations(BaseOperation oper);

	/*
	 * 检查并返回指定玩家的点杠操作
	 */	
	public DianGangOperation checkDianGangOperation(BaseOperation daOper);
	
}
