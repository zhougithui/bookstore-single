package org.bear.bookstore.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RandomTest {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Random r = new Random();
		Map<Integer, Object> map = new ConcurrentHashMap<>();
		for(int i=0; i<100; i++){
			service.execute((()->{
				for(int j=0; j<500; j++){
					if(map.put(r.nextInt(), "") != null){
						System.out.println("fdsa");
					}
				}
			}));
		}
		System.out.println(new UUID(ThreadLocalRandom.current().nextLong(), ThreadLocalRandom.current().nextLong()));
		service.awaitTermination(10, TimeUnit.SECONDS);
		service.shutdown();
	}
}
