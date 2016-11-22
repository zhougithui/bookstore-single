package org.bear.bookstore.test.propagation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/test/resources/spring-app.xml","file:src/test/resources/spring-hibernate.xml"}
		)
public class RequiredTest {
	@Autowired Required required;
	
	@Before
	public void init(){
		TransactionSynchronizationManager.initSynchronization();
	}
	
	@Test
	public void requiredTest(){
		required.a();
	}
}
