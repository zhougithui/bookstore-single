package org.bear.bookstore.test.messageresource;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageResourceTest {
	public static void main(String[] args) {
		MessageSource resources = new ClassPathXmlApplicationContext("beans.xml", MessageResourceTest.class);
	    String message = resources.getMessage("message", null, "Default", null);
	    System.out.println(message);
	    String message1 = resources.getMessage("argument.required", new Object[]{"customDao"}, "Required", null);
	    System.out.println(message1);
	    
	    
	    ((ClassPathXmlApplicationContext)resources).close();
	}
}
