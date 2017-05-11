package org.bear.bookstore.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> f = (FutureTask<String>) Executors.newCachedThreadPool().submit(()->{
			return "fdasfad";
		});
		
		System.out.println(f.get());
	}
}
