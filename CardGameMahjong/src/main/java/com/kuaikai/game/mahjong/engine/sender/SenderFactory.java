package com.kuaikai.game.mahjong.engine.sender;

import com.kuaikai.game.common.play.GameDesk;

public class SenderFactory {
	
	public static DefaultMessageSender createMessageSender(GameDesk desk) {
		switch(desk.getRule()) {
		case LIANG :
			//return new LiangMessageSender(desk);
		default :
			return new DefaultMessageSender(desk);
		}
	}
	
}
