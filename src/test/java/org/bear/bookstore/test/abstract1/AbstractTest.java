package org.bear.bookstore.test.abstract1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbstractTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = 
				new ClassPathXmlApplicationContext("classpath:spring-abstract.xml");
		
		ac.registerShutdownHook();
		AbstractManager manager = ac.getBean("manager",AbstractManager.class);
		manager.hello();
		manager.hello();
		manager.hello();
		manager.hello();
		manager.hello();
		ac.close();
	}
}
