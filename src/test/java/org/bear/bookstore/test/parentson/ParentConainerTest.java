package org.bear.bookstore.test.parentson;

import org.bear.bookstore.test.parentson.son.A;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ParentConainerTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = 
				new ClassPathXmlApplicationContext("classpath:spring-app-parent.xml");
		
		ac.registerShutdownHook();
		
		ClassPathXmlApplicationContext acSon = 
				new ClassPathXmlApplicationContext(new String[]{"classpath:spring-app-son.xml"}, ac);
		
		acSon.registerShutdownHook();
		
		A a = acSon.getBean(A.class);
		a.hello();
		
		acSon.close();
		ac.close();
	}
}
