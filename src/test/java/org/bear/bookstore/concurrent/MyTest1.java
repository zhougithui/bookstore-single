package org.bear.bookstore.concurrent;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class MyTest1 {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(1); //此处可使用CountDownLatch(1)
		for(int i=0; i<3; i++) {
			new MyTest1().new MyThread((char)(97+i), phaser).start();
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phaser.arrive();  //此处可使用latch.countDown()
	}
	
	class MyThread extends Thread {
		private char c;
		private Phaser phaser;
		
		public MyThread(char c, Phaser phaser) {
			this.c = c;
			this.phaser = phaser;
		}
		
		@Override
		public void run() {
			phaser.awaitAdvance(phaser.getPhase()); //此处可使用latch.await()
			for(int i=0; i<100; i++) {
				System.out.print(c+" ");
				if(i % 10 == 9) {
					System.out.println();
				}
			}
		}
	}
}
