package com.kuaikai.game.common.setting;

import com.kuaikai.game.common.event.BaseEvent;
import com.kuaikai.game.common.event.BaseListener;
import com.kuaikai.game.common.event.login.LoginEvent;

public class LoginListener implements BaseListener {

	@Override
	public <T> void onEventTriggered(BaseEvent<T> baseEvent) {
		LoginEvent loginEvent = (LoginEvent) baseEvent;
		int uid = loginEvent.t;
		GlobalSettingManager.onUserLogin(uid);
	}

}
