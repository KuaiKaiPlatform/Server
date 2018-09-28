package com.kuaikai.game.mahjong.module;

import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.login.LoginEvent;
import com.kuaikai.game.mahjong.test.LoginListener;

public class GameTestModule {

	public static void init() {
		TriggerManager.registerListener(LoginEvent.class, new LoginListener());
	}
	
}
