package org.bear.bookstore.retry;

import org.bear.bookstore.TaskUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRetryTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("retry.xml", SpringRetryTest.class);
		ac.registerShutdownHook();
		
		RetryService service = ac.getBean(RetryService.class);
		
		TaskUtils.submit(()->{
			service.service("111");
		});
		
		TaskUtils.submit(()->{
			service.service("222");
		});
		
		ac.close();
	}
}
