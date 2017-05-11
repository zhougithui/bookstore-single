package org.bear.bookstore.concurrent;

import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(10);
		threadPool.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
		threadPool.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);
		
		for(int i=0; i<10; i++){
			int j = i;
			threadPool.schedule(new Thread(()->{
				System.out.println("aaa" + j);
			},"" + j), 10, TimeUnit.SECONDS);
		}
		Iterator<Runnable> iter = threadPool.getQueue().iterator();
		
		while(iter.hasNext()){
			Runnable nex = iter.next();
			System.out.println(nex);
		}
		System.out.println();
		threadPool.shutdown();
	}
}

