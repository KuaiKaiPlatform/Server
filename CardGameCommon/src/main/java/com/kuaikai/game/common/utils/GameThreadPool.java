package com.kuaikai.game.common.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GameThreadPool {
	
	private static final ThreadPoolManager threadPool = new ThreadPoolManager(50, 200, 10, TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>(), "game-threadpool-");

	private static final ScheduledThreadPoolManager scheduler = new ScheduledThreadPoolManager(50, "schdl-threadpool-");
	
	public static ThreadPoolManager getThreadPool() {
		return threadPool;
	}

	public static void shutDown() {
		threadPool.shutdown();
	}
	
	public static ScheduledThreadPoolManager getScheduler() {
		return scheduler;
	}
	
}
