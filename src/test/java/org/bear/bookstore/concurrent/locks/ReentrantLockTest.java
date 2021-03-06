package org.bear.bookstore.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	/**
	 * 独占锁，分公平和非公平锁
	 * 有一个Node链式存储结构，公平锁会判断是否有比当前线程等待时间更长的线程
	 * 非公平锁不会判断
	 * 
	 * 通过for(;;)和Unsafe.park(false,0L)来实现自旋
	 */
	ReentrantLock lock = new ReentrantLock(true);
	
	int count = 0;
	public void incr(){
		System.out.println(count + ",thread Name :" + Thread.currentThread().getName());
		//获取锁，通过锁的状态获取，0为可用状态、1为不可用
		lock.lock();
		count++;
		System.out.println(count + ",-----thread Name :" + Thread.currentThread().getName());
		//还原锁的状态
		lock.unlock();
	}
	
	public void x(){
		boolean b = false;
		try {
			b = lock.tryLock(1, TimeUnit.SECONDS);
			System.out.println(b);
		} catch (InterruptedException e) {
			System.out.println("fdsafdsaxxxxxx");
			e.printStackTrace();
		}
		System.out.println("get lock thread:" + Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("fdsafdsafsda-------");
		if(b){
			lock.unlock();
		}
	}
	public void tryLockTest(){
		new Thread(() -> {
			x();
		}).start();
		new Thread(() -> {
			x();
		}).start();
	}
	public static void main(String[] args) {
		ReentrantLockTest test = new ReentrantLockTest();
		/*for(int i=0; i<10; i++){
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					test.incr();
				}
			}, i+"");
			t.start();
		}*/
		test.tryLockTest();
	}
}
