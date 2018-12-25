package com.kuaikai.game.logic.module;

import com.kuaikai.game.logic.desk.GameDeskModule;
import com.kuaikai.game.logic.prepare.GamePrepareModule;

public class GameModules {

	public static void init() {
		GamePrepareModule.init();
		GameDeskModule.init();
	}
	
}
