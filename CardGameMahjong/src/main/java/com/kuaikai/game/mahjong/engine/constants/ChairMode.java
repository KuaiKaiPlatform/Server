package com.kuaikai.game.mahjong.engine.constants;

/**
 * 选座模式
 */
public enum ChairMode {

    SEQUENCE,	// 按加入房间顺序入座
    PLAYER,		// 玩家选座
    RANDOM;		// 随机选座
    
    public boolean checkMode(String mode) {
    	return this.equals(ChairMode.valueOf(mode));
    }
    
}
