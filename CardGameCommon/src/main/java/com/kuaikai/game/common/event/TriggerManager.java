package com.kuaikai.game.common.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriggerManager {
	private static final TriggerManager instance = new TriggerManager();

	private static final Logger LOGGER = LoggerFactory.getLogger(TriggerManager.class);

	private Map<String, List<BaseListener>> listeners = new HashMap<String, List<BaseListener>>();

	/**
	 * 触发事件
	 * 
	 * @param event
	 */
	public static <T> void triggerEvent(BaseEvent<T> event) {
		String eventName = event.getClass().getName();
		List<BaseListener> listeners = instance.listeners.get(eventName);
		if (listeners == null) {
			LOGGER.warn("TriggerManager.triggerEvent@listeners not found|eventName={}", eventName);
			return;
		}
		for (BaseListener listener : listeners) {
			try {
				listener.onEventTriggered(event);
			} catch (Exception e) {
				LOGGER.error(String.format("TriggerManager.triggerEvent@error occured|eventName=%s", eventName), e);
			}

		}

	}

	/**
	 * 必须在server启动的时候注册 非线程安全的
	 * 
	 * @param event
	 * @param baseListener
	 */
	public static <T> void registerListener(Class<? extends BaseEvent<T>> clss, BaseListener baseListener) {
		String eventName = clss.getName();
		List<BaseListener> listeners = instance.listeners.get(eventName);
		if (listeners == null) {
			listeners = new LinkedList<BaseListener>();
			instance.listeners.put(eventName, listeners);
		}
		listeners.add(baseListener);
	}

}
