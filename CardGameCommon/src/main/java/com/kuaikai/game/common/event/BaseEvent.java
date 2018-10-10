package com.kuaikai.game.common.event;

public abstract class BaseEvent<T> {
	// 信息的参数
	public final T t;

	public BaseEvent(T t) {
		this.t = t;
	}
}
