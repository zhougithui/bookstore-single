package org.bear.bookstore;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskUtils {
	private static ThreadPoolExecutor threadPool = 
			new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
	static{
		threadPool.allowCoreThreadTimeOut(true);
	}
	public static void submit(Runnable task){
		threadPool.execute(task);
	}
	
}
