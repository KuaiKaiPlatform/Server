package com.kuaikai.game.common.tcp;

import java.lang.reflect.Method;

import com.kuaikai.game.common.utils.ClassUtils;

public class Modules {

	public static void init(String [] packages) throws Exception {
		for(String packageName : packages) {
			init(packageName);
		}
	}
	
	public static void init(String packageName) throws Exception {
		for (String className : ClassUtils.getClassName(packageName, true)) {
			Class<?> clazz = Class.forName(className);
			Method m = clazz.getDeclaredMethod("init");
			if(m != null) m.invoke(null);
		}
	}
	
}
