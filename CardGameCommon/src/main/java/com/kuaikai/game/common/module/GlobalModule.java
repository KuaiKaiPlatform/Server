package com.kuaikai.game.common.module;

import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.login.LoginEvent;
import com.kuaikai.game.common.setting.LoginListener;

public class GlobalModule {

	public static void init() {
		TriggerManager.registerListener(LoginEvent.class, new LoginListener());
	}
	
}
