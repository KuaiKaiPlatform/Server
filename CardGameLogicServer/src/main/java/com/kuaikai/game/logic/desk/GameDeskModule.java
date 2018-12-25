package com.kuaikai.game.logic.desk;

import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.login.LoginEvent;

public class GameDeskModule {

	public static void init() {
		TriggerManager.registerListener(LoginEvent.class, new LoginListener());
		DeskManager.init();
	}
	
}
