package org.bear.bookstore.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author zhou.hui 2017-08-10 15:57:57
 */
public class RedisTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("redis.xml", RedisTest.class);
		ac.registerShutdownHook();
		
		RedisService redis = ac.getBean(RedisService.class);
		List<String> names = new ArrayList<>();
		names.add("1");
		names.add("2");
		names.add("3");
		names.add("4");
		names.add("5");
		redis.listService().cacheList("names", names );
		
		System.out.println(redis.listService().getList("names", 0, 4));
		
		ac.close();
	}
}
