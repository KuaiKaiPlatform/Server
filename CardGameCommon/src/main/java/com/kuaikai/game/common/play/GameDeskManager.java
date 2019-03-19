package com.kuaikai.game.common.play;

import java.util.concurrent.ConcurrentHashMap;

public class GameDeskManager {

	private static final ConcurrentHashMap<String, GameDesk> map = new ConcurrentHashMap<String, GameDesk>();
	
	public static void add(GameDesk desk) {
		map.put(desk.getKey(), desk);
	}

	public static void remove(GameDesk desk) {
		map.remove(desk.getKey());
	}
	
	public static GameDesk get(String deskKey) {
		return map.get(deskKey);
	}
	
}
