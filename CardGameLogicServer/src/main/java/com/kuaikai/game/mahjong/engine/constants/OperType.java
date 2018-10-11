package com.kuaikai.game.mahjong.engine.constants;

import java.util.HashSet;
import java.util.Set;

public class OperType {
	// 按优先级排序
	public static final int MO				= 10;							// 摸
	public static final int DA				= 20;							// 打
	public static final int CHI				= 30;							// 吃
	public static final int PENG			= 40;							// 碰
	
	public static final int PENG_FAN		= 45;							// 碰翻（摸牌后手牌有三张万能牌的翻牌，可以翻开作为明牌，之后再打牌）
	
	public static final int BU_GANG			= 50;							// 补杠
	public static final int AN_GANG			= 60;							// 暗杠
	public static final int DIAN_GANG		= 70;							// 点杠
	public static final int TING			= 80;							// 听
	public static final int HU				= 90;							// 胡
	
	private static Set<Integer> operTypes = new HashSet<Integer>();
	static {
		operTypes.add(MO);
		operTypes.add(DA);
		operTypes.add(CHI);
		operTypes.add(PENG);
		
		operTypes.add(PENG_FAN);
		
		operTypes.add(BU_GANG);
		operTypes.add(AN_GANG);
		operTypes.add(DIAN_GANG);
		operTypes.add(TING);
		operTypes.add(HU);
	}
	
	public static boolean isGang(int operType) {
		return operType == BU_GANG || operType == AN_GANG || operType == DIAN_GANG;
	}
	
	public static boolean isValid(int operType) {
		return operTypes.contains(operType);
	}
	
}
