package com.kuaikai.game.logic.play;

import java.util.LinkedList;
import java.util.List;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.mahjong.engine.model.Mahjong;

/**
 * 游戏牌桌，定义通用操作
 *
 */
public abstract class GameDesk {

	protected Desk desk;
	
	protected GamePlayer banker;
	protected List<GamePlayer> players = new LinkedList<GamePlayer>();
	
	protected GameDesk(Desk desk) {
		this.desk = desk;
	}
	
	public GameRule getRule() {
		return desk.getRule();
	}
	
	public AttrsModel getSetting() {
		return desk.getSetting();
	}
	
	public String getKey() {
		return desk.getKey();
	}
	
	public GamePlayer getBanker() {
		return banker;
	}

	public void setBanker(GamePlayer banker) {
		this.banker = banker;
	}
	
	public List<GamePlayer> getAllPlayers() {
		return players;
	}
	
	public GamePlayer getNextPlayer(GamePlayer gamePlayer) {
		return getNextPlayer(gamePlayer.player.getSeat());
	}
	
	public GamePlayer getNextPlayer(int seat) {
		int nextSeat = seat % Mahjong.POS_MAX + 1;
		Player player = desk.getPlayerBySeat(nextSeat);
		return player == null?getNextPlayer(nextSeat):findGamePlayer(player);
	}
	
	private GamePlayer findGamePlayer(Player player) {
		for(GamePlayer p : players) {
			if(p.player.equals(player)) return p;
		}
		return null;
	}
	
	public abstract void onGameStart();
	
	public abstract void onGameEnd();
	
}
