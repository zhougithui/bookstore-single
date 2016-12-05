package org.bear.bookstore.test.xml;

import org.springframework.beans.factory.annotation.Autowired;

public class MySpringBean3 {
	@Autowired
	private MySpringBean2 bean;
	
	public void hello() {
		System.out.println("hello3 world!");
		bean.hello();
	}

}
