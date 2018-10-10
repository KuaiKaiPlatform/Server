package com.kuaikai.game.mahjong.model;

import com.kuaikai.game.common.model.Desk;

public class MahjongDesk {

	protected Desk desk;
	
	public boolean isFull() {
		return desk.getPlayers().size() >= 4;
	}
	
}
