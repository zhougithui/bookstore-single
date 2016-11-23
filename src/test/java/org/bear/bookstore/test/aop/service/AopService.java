package org.bear.bookstore.test.aop.service;

import org.springframework.stereotype.Component;

@Component("aopService")
public class AopService {
	
	public void set(String name){
		System.out.println(name);
	}
}
