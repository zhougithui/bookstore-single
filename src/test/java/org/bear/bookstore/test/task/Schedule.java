package org.bear.bookstore.test.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
	/**
	 * 方法不能够有参数，否则抛出异常
	 * 		Only no-arg methods may be annotated with @Scheduled
	 * 
	 * 系统启动会自动执行
	 */
	@Scheduled(fixedRate=1000)
	public void hello(){
		System.out.println("hello zhxxx");
	}
}
