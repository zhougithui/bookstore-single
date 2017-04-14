package org.bear.bookstore.concurrent.locks;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

class FIFOMutex {
	private final AtomicBoolean locked = new AtomicBoolean(false);
	private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

	public void lock() {
		boolean wasInterrupted = false;
		Thread current = Thread.currentThread();
		waiters.add(current);
		System.out.println(waiters.size());

		// Block while not first in queue or cannot acquire lock
		while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
			System.out.println("------------");
			LockSupport.park(current);
			if (Thread.interrupted()) // ignore interrupts while waiting
				wasInterrupted = true;
		}

		System.out.println("remove thread");
		waiters.remove();
		if (wasInterrupted) // reassert interrupt status on exit
			current.interrupt();
	}

	public void unlock() {
		locked.set(false);
		Thread t = waiters.peek();
		System.out.println("unlock:" + t.getName());
		LockSupport.unpark(t);
	}
	
	public static void main(String[] args) {
		final FIFOMutex mutex = new FIFOMutex();
		for(int i=0; i<10; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					mutex.lock();
					System.out.println("thread name==" + Thread.currentThread().getName());
				}
			},i+"fdsa").start();
		}
		
		for(int i=0; i<100; i++){
			mutex.unlock();
		}
	}
	
}