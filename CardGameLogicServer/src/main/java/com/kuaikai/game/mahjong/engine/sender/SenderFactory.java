package com.kuaikai.game.mahjong.engine.sender;

import com.kuaikai.game.mahjong.engine.sender.shaanxi.LiangMessageSender;

public class SenderFactory {
	
	public static MessageSender createMessageSender(RoomExtension roomExt) {
		switch(roomExt.getRoom().getCreateRoomParam().getRule()) {
		case LIANG :
			return new LiangMessageSender(roomExt);
		default :
			return new MessageSender(roomExt);
		}
	}
	
}
