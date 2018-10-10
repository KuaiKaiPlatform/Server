package com.kuaikai.game.common.event.desk;

import com.kuaikai.game.common.event.BaseEvent;

public class DeskStartEvent extends BaseEvent<String> {

	public DeskStartEvent(String deskKey) {
		super(deskKey);
	}

}
