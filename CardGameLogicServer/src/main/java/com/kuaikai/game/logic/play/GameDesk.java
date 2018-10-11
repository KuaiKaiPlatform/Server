package com.kuaikai.game.logic.play;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;

/**
 * 游戏牌桌，定义通用操作
 *
 */
public abstract class GameDesk {

	protected Desk desk;
	
	protected GameDesk(Desk desk) {
		this.desk = desk;
	}
	
	public GameRule getRule() {
		return desk.getRule();
	}
	
	public AttrsModel getSetting() {
		return desk.getSetting();
	}
	
	public String getKey() {
		return desk.getKey();
	}
	
	public abstract void onGameStart();
	
	public abstract void onGameEnd();
	
}
