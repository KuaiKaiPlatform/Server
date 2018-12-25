package com.kuaikai.game.logic.prepare;

import com.kuaikai.game.common.event.TriggerManager;
import com.kuaikai.game.common.event.login.LoginEvent;

/**
 * 玩家登录后执行游戏准备
 * @author alie3
 *
 */
public class GamePrepareModule {

	public static void init() {
		TriggerManager.registerListener(LoginEvent.class, new LoginListener());
	}
	
}
