package org.bear.bookstore.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
	private int count = 0;
	
	private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
	public void r(){
		try {
			rw.readLock().lock();
			System.out.println("read count " + count + "$$$$$getReadLockCount=" + rw.getReadLockCount()
				+ "$$$$getWriteHoldCount()" + rw.getWriteHoldCount());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			rw.readLock().unlock();
		}
	}
	public void w(){
		try {
			rw.writeLock().lock();
			count++;
			System.out.println("write count " + count + "$$$$$$getQueueLength=" + rw.getQueueLength()
					+ "$$$$getWriteHoldCount()" + rw.getWriteHoldCount());
			Thread.yield();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			rw.writeLock().unlock();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
		
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i=0; i<10; i++){
			service.execute(()->{
				int x = 0;
				while(x<10){
					test.r();
					x++;
				}
			});
		}
		
		for(int i=0; i<2; i++){
			service.execute(()->{
				int x = 0;
				while(x<100){
					test.w();
					x++;
				}
			});
		}
		
		TimeUnit.SECONDS.sleep(3);
		service.shutdownNow();
	}
}

