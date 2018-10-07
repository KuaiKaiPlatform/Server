package com.kuaikai.game.hall.module;

import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.login.LoginEvent;
import com.kuaikai.game.hall.test.DeskManager;
import com.kuaikai.game.hall.test.LoginListener;

public class GameTestModule {

	public static void init() {
		TriggerManager.registerListener(LoginEvent.class, new LoginListener());
		DeskManager.init();
	}
	
}
