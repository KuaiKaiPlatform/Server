package com.kuaikai.game.common.play;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.Player;

/**
 * 游戏玩家，定义通用操作
 *
 */
public abstract class GamePlayer {

	protected GameDesk gameDesk;
	protected Player player;
	
	// 一局游戏玩家属性
	protected AttrsModel setAttrs = new AttrsModel();
	
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

	public int getSeat() {
		return player.getSeat();
	}

	public int getBet() {
		return player.getBet();
	}
	
	public void setBet(int bet) {
		player.setBet(bet);
	}
	
	public boolean isOffline() {
		return player.isOffline();
	}

	public void setOffline(boolean offline) {
		player.setOffline(offline);
	}
	
	public int getDelta(GamePlayer other) {
		return player.getDelta(other.player);
	}
	
	public void clearSetAttrs() {
		setAttrs.clear();
	}
	
	public AttrsModel getSetAttrs() {
		return setAttrs;
	}
	
	public void onReady() {
		player.setPrepared(true);
	}

	public boolean isReady() {
		return player.isPrepared();
	}
	
	public Player getPlayer() {
		return player;
	}

	public abstract void onGameStart();
	
	public abstract void onGameEnd();
	
	public void onSetStart() {
		player.setPrepared(false);
	}
	
	public abstract void onSetEnd();
	
}
