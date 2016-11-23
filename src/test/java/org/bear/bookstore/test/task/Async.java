package org.bear.bookstore.test.task;

import org.springframework.stereotype.Component;

@Component
public class Async {
	
	@org.springframework.scheduling.annotation.Async
	public String hello(String name){
		if(name.equals("zh3")){
			int i = 1/0;
			System.out.println(i);
		}
		System.out.println("hello " + name);
		return "hello " + name;
	}
}
