package com.kuaikai.game.logic.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.event.BaseEvent;
import com.kuaikai.game.common.event.BaseListener;
import com.kuaikai.game.common.event.desk.DeskStartEvent;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.redis.DeskRedis;

public class DeskStartListener implements BaseListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeskStartListener.class);
	
	@Override
	public <T> void onEventTriggered(BaseEvent<T> baseEvent) {
		DeskStartEvent deskStartEvent = (DeskStartEvent) baseEvent;
		String deskKey = deskStartEvent.t;
		Desk desk = DeskRedis.getDesk(deskKey);
		LOGGER.info("com.kuaikai.game.logic.listener.onEventTriggered@desk={}", desk);
	}

}
