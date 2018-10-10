package com.kuaikai.game.mahjong.module;

import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.desk.DeskStartEvent;
import com.kuaikai.game.logic.listener.DeskStartListener;

public class GameTestModule {

	public static void init() {
		TriggerManager.registerListener(DeskStartEvent.class, new DeskStartListener());
	}
	
}
