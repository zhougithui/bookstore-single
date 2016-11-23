package org.bear.bookstore.test.aop;

import org.bear.bookstore.test.aop.service.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/test/resources/spring-aop.xml"}
		)
public class AopTest {
	
	@Autowired ApplicationContext ctx;
	
	//@Test
	public void debugTest(){
		AopService ser = (AopService)ctx.getBean("proxyFactory");
		ser.set("zh");
	}
	
	@Autowired AopService aopService;
	
	@Test
	public void debug1Test(){
		aopService.set("zh11");
	}
}
