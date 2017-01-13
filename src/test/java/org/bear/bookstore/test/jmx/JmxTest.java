package org.bear.bookstore.test.jmx;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmxTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("beans.xml", JmxTest.class);
	    
		AnnotationTestBean bean = app.getBean(AnnotationTestBean.class);
		TimeUnit.SECONDS.sleep(5);
		
		/**
		 * 此处修改，在客户端不生效，客户端通过jmx获取MBean获取的值仍是注册MBean之前的值
		 */
		bean.setAge(20);
		System.out.println(app.getBean(AnnotationTestBean.class).getName());
		
		System.in.read();
	    ((ClassPathXmlApplicationContext)app).close();
	}
}
