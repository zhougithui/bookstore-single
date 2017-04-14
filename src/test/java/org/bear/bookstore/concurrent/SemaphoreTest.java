package org.bear.bookstore.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) throws InterruptedException {
		Semaphore s = new Semaphore(1);
		s.acquire(1);
		s.release();
	}
}
