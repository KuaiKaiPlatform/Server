package com.kuaikai.game.common.conf;

import java.util.Properties;

public class PropertyManager {

	public static Properties properties;
	
	public static void init(Properties properties) {
		PropertyManager.properties = properties;
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static String get(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
	
	public static boolean isDebug() {
		return "true".equalsIgnoreCase(get("DEBUG"));
	}
}
