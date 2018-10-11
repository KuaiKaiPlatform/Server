package com.kuaikai.game.mahjong.engine.model;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.logic.play.GameDesk;
import com.kuaikai.game.mahjong.engine.MahjongEngine;

public class MahjongDesk extends GameDesk {

	private MahjongEngine engine;
	
	public MahjongDesk(Desk desk) {
		super(desk);
		engine = new MahjongEngine(this);
	}

	public MahjongEngine getEngine() {
		return engine;
	}

	@Override
	public void onGameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameEnd() {
		// TODO Auto-generated method stub
		
	}
	
}
