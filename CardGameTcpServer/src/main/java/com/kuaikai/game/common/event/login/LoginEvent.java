package com.kuaikai.game.common.event.login;

import com.kuaikai.game.common.event.BaseEvent;

public class LoginEvent extends BaseEvent<Integer> {

	public LoginEvent(int uid) {
		super(uid);
	}

}
