package org.bear.bookstore.test.jmx;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmxTest {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("beans.xml", JmxTest.class);
	    
	    
		
		System.in.read();
	    ((ClassPathXmlApplicationContext)app).close();
	}
}
