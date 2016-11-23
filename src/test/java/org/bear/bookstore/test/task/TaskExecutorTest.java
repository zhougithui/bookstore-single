package org.bear.bookstore.test.task;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/test/resources/spring-task.xml"}
		)
public class TaskExecutorTest {
	@Autowired Async Async;
	
	/**
	 * spring的taskExecutor测试
	 */
	@Test
	public void AsyncTest(){
		for(int i=0; i<10;i++){
			System.out.println(Async.hello("zh" + i));
		}
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
