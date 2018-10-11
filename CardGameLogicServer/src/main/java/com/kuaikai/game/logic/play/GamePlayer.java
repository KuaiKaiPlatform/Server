package com.kuaikai.game.logic.play;

import com.kuaikai.game.common.model.Player;

/**
 * 游戏玩家，定义通用操作
 *
 */
public abstract class GamePlayer {

	protected GameDesk gameDesk;
	protected Player player;
	
	protected GamePlayer(Player player, GameDesk desk) {
		this.player = player;
		this.gameDesk = desk;
	}
	
	public boolean isBanker() {
		return this.equals(gameDesk.getBanker());
	}
	
	public GameDesk getGameDesk() {
		return gameDesk;
	}
	
	public int getId() {
		return player.getId();
	}
	
	public int getDelta(GamePlayer other) {
		return player.getDelta(other.player);
	}
	
}
