package org.bear.bookstore.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	
	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(10);
		
		new Thread(() -> {
			try {
				System.out.println("等待我的10名队员");
				cdl.await();
				System.out.println("人员到齐，出发");
				
				
				
				new Thread(() -> {
					try {
						System.out.println("等待我的10名队员");
						cdl.await();
						System.out.println("人员到齐，出发.......");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
				for(int i=10; i<20; i++){
					new Thread(() -> {
						System.out.println("队员来了：" + Thread.currentThread().getName());
						cdl.countDown();
					}, ""+i).start();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		for(int i=0; i<10; i++){
			new Thread(() -> {
				cdl.countDown();
				System.out.println("队员来了：" + Thread.currentThread().getName());
			}, ""+i).start();
		}
		
	}
}
