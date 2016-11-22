package org.bear.bookstore.test.propagation;

import org.bear.bookstore.test.propagation.jdbc.Nested;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/test/resources/spring-app-jdbc.xml","file:src/test/resources/spring-jdbc.xml"}
		)
public class JdbcTransactionPropagationTest {
	/**
	 * 运行在datasourcemanager中
	 */
	@Autowired Nested Nested;
	/**
	 * PROPAGATION_NESTED 
	 * 	
	 */
	@Test
	public void NestedTest(){
		//Nested.b();
		Nested.a();
	}
}
