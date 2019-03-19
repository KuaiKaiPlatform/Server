package com.kuaikai.game.common.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolManager extends ThreadPoolExecutor {

	public ThreadPoolManager(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue,final String namePrefix) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,new ThreadFactory() {
			
			private final AtomicInteger atomicInteger = new AtomicInteger(0);
			
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r, namePrefix+ atomicInteger.getAndIncrement());
				thread.setDaemon(true);
				return thread;
			}
		});
	}
	
    public static void main(String[] args) {
    	//注意在进程结束的时候调用一下 poolManager.shutdown();
    	ThreadPoolManager poolManager =  new ThreadPoolManager(50,200,10,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),"db-threadpool-");
    	//最好把runnable 实现成具体的类
    	poolManager.execute(new Runnable() {
			@Override
			public void run() {
				
			}
		});
	}

}
