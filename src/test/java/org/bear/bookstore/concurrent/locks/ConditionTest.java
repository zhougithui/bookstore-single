package org.bear.bookstore.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition必须在lock块中使用，为
 * @see java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject
 * 的一个实例
 * 每当有一个线程执行await都会阻塞当前线程，放入Node链表中  
 * ConditionObject firstWaiter lastWaiter
 * Node 
 * 		pre next thread 
 * 		thread nextWaiter
 * LockSupport.park阻塞当前线程
 * 每调用一次signal就会调用LockSupport.unpark(node.thread)
 * 
 * 
 * @author q
 *
 */
public class ConditionTest {
	ReentrantLock lock = new ReentrantLock();
	Condition incr = lock.newCondition();
	Condition decr = lock.newCondition();
	int count = 0;
	public void incr(){
		lock.lock();
		try {
			while(count <= 10){
				while(count < 10){
					count++;
					System.out.println("incr count:" + count);
				}
				decr.signal();
				System.out.println("incr");
				incr.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public void decr(){
		lock.lock();
		if(count<10)
			try {
				System.out.println("decr");
				decr.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		while(count >= 10){
			while(count >= 10){
				count--;
				System.out.println("decr count:" + count);
			}
			incr.signal();
			try {
				decr.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
	}
	public static void main(String[] args) {
		/*ConditionTest c = new ConditionTest();
		new Thread(() -> {
			c.incr();
		}).start();;
		new Thread(() -> {
			c.decr();
		}).start();*/
		
		final ReentrantLock lock = new ReentrantLock();
		Condition c = lock.newCondition();
		for(int i=0;i<10;i++){
			new Thread(() -> {
				lock.lock();
				System.out.println("await" + Thread.currentThread().getName());
				try {
					c.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("醒来了：" + Thread.currentThread().getName());
				lock.unlock();
			}, "" + i).start();
		}
		for(int i=0;i<5;i++){
			new Thread(() -> {
				lock.lock();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c.signal();
				System.out.println("signal");
				lock.unlock();
			}).start();
		}
	}
}
