package org.bear.bookstore.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * 	分配对象可能产生GC压力，特别是当任务运行时间过长。
		每次 GC 暂停都会影响全局性能。
	默认，队列是无界的，任务会因为数据库调用而堆积。
		积压虽然不会直接导致内存泄漏，但会带来严重副作用：GC 暂停时要扫描更多的对象；有丢失重要数据位的风险；等等 …
		典型链式队列节点分配时会产生大量内存压力。
	阻塞回调容易产生恶性循环。
		阻塞回调会降低消息生产者的效率。在实践中，任务提交后需要等待结果返回，此时流式过程几乎演变为同步的了。
		会话过程抛出的任何带数据存储的异常都会以不受控的方式被传递给生产者，否定了任何通常在线程边界附近可用的容错性
 * @author q
 *
 */
public class R1 {
	private static ExecutorService  threadPool = Executors.newFixedThreadPool(8);
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final List<String> batches = new ArrayList<String>();
		
		//Callable 分配 -- 可能导致 GC 压力
		Callable<String> t = new Callable<String>() { // *1
		
		        public String call() {
		        	//同步过程强制每个线程执行停-检查操作。
	                synchronized(batches) { // *2
	                	
	                		//消息的消费可能比生产慢。
	                        String result = callDatabase("fdsa"); // *3
	                        batches.add(result);
	                        return result;
	                }
		        }

				private String callDatabase(String string) {
					return null;
				}
		};
		
		//使用线程池(ThreadPool)将任务传递给目标线程 -- 通过 FutureTask 方式肯定会产生 GC 压力
		Future<String> f = threadPool.submit(t); // *4
		//阻塞直至 callDatabase() 回调
		String result = f.get(); // *5
	}
}

