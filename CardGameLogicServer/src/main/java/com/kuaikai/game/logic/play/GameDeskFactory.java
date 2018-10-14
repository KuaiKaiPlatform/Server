package com.kuaikai.game.logic.play;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.play.CardGameRule;
import com.kuaikai.game.common.play.GameDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;

public class GameDeskFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameDeskFactory.class);
	
	public static GameDesk create(Desk desk) {
		if(CardGameRule.isMahjong(desk.getRule())) {
			return new MahjongDesk(desk);
		}
		return null;
	}
	
}
