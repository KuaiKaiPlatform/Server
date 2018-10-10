package com.kuaikai.game.common.utils;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class CollectionUtils {

	private static final Logger logger = Logger.getLogger(CollectionUtils.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void increase(Map map, Object key) {
		if(map.containsKey(key)) {
			map.put(key, (Integer)map.get(key) + 1);
		} else {
			map.put(key, 1);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void increaseBy(Map map, Object key, int delta) {
		if(map.containsKey(key)) {
			map.put(key, (Integer)map.get(key) + delta);
		} else {
			map.put(key, delta);
		}
	}
	
	public static boolean isSame(List<?> list, int length) {
		Object o = list.get(0);
		for(Object obj : list) {
			if(!o.equals(obj)) return false;
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public static String getMapStr(Map map, Object key) {
		Object val = map.get(key);
		return (val == null)?"":val.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static int getMapInt(Map map, Object key) {
		Object val = map.get(key);
		if(val == null) return 0;
		if(val instanceof Integer) return (Integer)val;
		try {
			return Integer.parseInt(val.toString());	
		} catch(Exception e) {
			logger.warn("map integer parsed error: key=" + key + ", value=" + val);
		}
		return 0;
	}

	@SuppressWarnings("rawtypes")
	public static long getMapLong(Map map, Object key) {
		Object val = map.get(key);
		if(val == null) return 0;
		if(val instanceof Long) return (Long)val;
		try {
			return Long.parseLong(val.toString());	
		} catch(Exception e) {
			logger.warn("map long parsed error: key=" + key + ", value=" + val);
		}
		return 0;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean getMapBool(Map map, Object key) {
		Object val = map.get(key);
		if(val == null) return false;
		if(val instanceof Boolean) return (Boolean)val;
		if(val instanceof Integer) return (Integer)val > 0;
		try {
			return Integer.parseInt(val.toString()) > 0;
		} catch(Exception e) {
			logger.warn("setting boolean parsed integer error: key=" + key + ", value=" + val);
		}
		try {
			return Boolean.parseBoolean(val.toString());
		} catch(Exception e) {
			logger.warn("setting boolean parsed error: key=" + key + ", value=" + val);
		}
		
		return false;
	}
	
}
