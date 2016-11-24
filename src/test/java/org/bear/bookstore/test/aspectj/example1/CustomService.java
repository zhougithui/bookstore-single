package org.bear.bookstore.test.aspectj.example1;

import org.springframework.stereotype.Service;

@Service
public class CustomService {
	
	@Idempotent
	public String hello(String name){
		System.out.println("hello " + name);
		int i = 1/0;
		return "hello " + name;
	}
}
