package com.kuaikai.game.common.event;

public interface BaseListener {
	public <T> void onEventTriggered(BaseEvent<T> baseEvent);
}
