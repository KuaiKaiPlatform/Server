package com.kuaikai.game.logic.module;

import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.login.LoginEvent;
import com.kuaikai.game.logic.desk.DeskManager;
import com.kuaikai.game.logic.desk.LoginListener;

public class GameDeskModule {

	public static void init() {
		TriggerManager.registerListener(LoginEvent.class, new LoginListener());
		DeskManager.init();
	}
	
}
