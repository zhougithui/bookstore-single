package org.bear.bookstore.test.xml;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

public class MySpringBean3 {
	@Autowired
	private MySpringBean2 bean;
	
	@Setter
	private MySpringBean2 springBean2;
	
	public void hello() {
		System.out.println("hello3 world!");
		System.out.println(bean != null);
		if(springBean2 != null){
			springBean2.hello();
		}else{
			bean.hello();
		}
	}

}
