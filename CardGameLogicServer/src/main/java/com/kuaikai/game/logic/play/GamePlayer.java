package com.kuaikai.game.logic.play;

import com.kuaikai.game.common.model.Player;

/**
 * 游戏玩家，定义通用操作
 *
 */
public abstract class GamePlayer {

	protected Player player;
	
	protected GamePlayer(Player player) {
		this.player = player;
	}
	
	public int getId() {
		return player.getId();
	}
	
}
