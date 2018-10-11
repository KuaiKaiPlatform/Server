package com.kuaikai.game.common.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
	private static final Random RANDOM = new Random();

	private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int LENGTH = chars.length();

	/**
	 * 获取指定数目的随机数 0~9，a~z，A~Z
	 * 
	 * @param size
	 * @return
	 */
	public static String getRandomString(int size) {
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < size; i++) {
			int rdm = RANDOM.nextInt(LENGTH);
			sf.append(chars.charAt(rdm));
		}
		return sf.toString();
	}

	/**
	 * 获取下一个随机数
	 * 
	 * @param n
	 *            n must > 0
	 * @return
	 */
	public static int nextInt(int n) {
		return RANDOM.nextInt(n);
	}

	public static int nextInt(int max, int min) {
		if (max == min) {
			return max;
		}
		return RANDOM.nextInt(max - min + 1) + min;
	}
	
	/**
	 * 返回指定范围的一个随机数，start和end都包含。
	 */
	public static int getRandomInt(int start, int end) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextInt(start, end+1);
	}
	
}
