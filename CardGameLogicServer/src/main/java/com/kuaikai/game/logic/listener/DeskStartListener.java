package com.kuaikai.game.logic.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.event.BaseEvent;
import com.kuaikai.game.common.event.BaseListener;
import com.kuaikai.game.common.event.desk.DeskStartEvent;
import com.kuaikai.game.common.mock.DeskMock;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.play.GameDesk;
import com.kuaikai.game.logic.play.GameDeskFactory;

public class DeskStartListener implements BaseListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeskStartListener.class);
	
	@Override
	public <T> void onEventTriggered(BaseEvent<T> baseEvent) {
		DeskStartEvent deskStartEvent = (DeskStartEvent) baseEvent;
		String deskKey = deskStartEvent.t;
		LOGGER.debug("com.kuaikai.game.logic.listener.DeskStartListener.onEventTriggered@desk={}", deskKey);
		
		Desk desk = DeskMock.getDesk(deskKey);
		
		GameDesk gameDesk = GameDeskFactory.create(desk);
		if(gameDesk == null) {
			LOGGER.warn("com.kuaikai.game.logic.listener.DeskStartListener.onEventTriggered@Unsupported game rule|desk={}", desk);
			return;
		}
		
		gameDesk.onGameStart(System.currentTimeMillis());
		
		gameDesk.getMessageSender().sendSSetInit(null);
		
		LOGGER.info("com.kuaikai.game.logic.listener.DeskStartListener.onEventTriggered@desk={}", desk);
	}

}
