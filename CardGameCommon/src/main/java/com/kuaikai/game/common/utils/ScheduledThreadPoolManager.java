package com.kuaikai.game.common.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledThreadPoolManager extends ScheduledThreadPoolExecutor {

	public ScheduledThreadPoolManager(int corePoolSize, String namePrefix) {
		super(50, new ThreadFactory() {
			
			private final AtomicInteger atomicInteger = new AtomicInteger(0);
			
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r, namePrefix + atomicInteger.getAndIncrement());
				thread.setDaemon(true);
				return thread;
			}
		});
		
	}
/*	
	public static void shutDown() {
		scheduler.shutdown();
	}

	public static ScheduledFuture schedule(Runnable command, long delay, TimeUnit unit) {
		return scheduler.schedule(command, delay, unit);
	}*/
	
}
