package org.bear.bookstore.thread;

import java.util.concurrent.TimeUnit;

public class ObjectTest {
	private static Object lock = new Object();
	
	public void f() throws InterruptedException{
		synchronized (lock) {
			lock.wait(0);
			//this.wait();//错误的调用方式
			for(int i=0; i<10; i++){
				System.out.println("xxx-" + i);
			}
		}
	}
	public void f1() throws InterruptedException{
		TimeUnit.SECONDS.sleep(3);
		System.out.println("notify lock thread");
		synchronized (lock) {
			lock.notify();
		}
	}
	
	public static void main(String[] args) {
		ObjectTest x = new ObjectTest();
		new Thread(()->{try {
			x.f();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}).start();;
		new Thread(()->{try {
			x.f1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}).start();;
	}
}
