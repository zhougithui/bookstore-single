package org.bear.bookstore.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(10, () -> {
			System.out.println("什么情况。。。。。。。");
		});
		for(int i=0; i<10; i++){
			new Thread(() -> {
				try {
					System.out.println("waiting:" + cb.getNumberWaiting() + ";thread:" + Thread.currentThread().getName());
					cb.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, "" + i).start();
		}
		while(cb.getNumberWaiting() < 6){
			
		}
		cb.reset();
		System.out.println("waiting....:" + cb.getNumberWaiting() + ";broken:" + cb.isBroken());
	}
}
