package org.bear.bookstore.test.propagation;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/test/resources/spring-app.xml","file:src/test/resources/spring-hibernate.xml"}
		)
public class TransactionPropagationTest {
	
	@Autowired Required required;
	/**
	 * PROPAGATION_REQUIRED
	 * 	当前方法必须运行在事务中，如果当前事务不存在则创建新的事务
	 *  required中a、b两个方法，a调用时创建新的事务，a调用b，b加入a的事务中
	 */
	//@Test
	public void requiredTest(){
		required.a();
	}
	
	@Autowired Supports supports;
	/**
	 * PROPAGATION_SUPPORTS
	 * 	当前方法不需要运行在事务中，如果事务存在，则事务的运行，否则非事务的运行
	 * 	单独执行b则事务不会回滚，配置的rollback无效，执行a，a中调用b，b就会回滚
	 */
	//@Test
	public void supportsTest(){
		//supports.b();
		supports.a();
	}
	
	@Autowired Mandatory Mandatory;
	/**
	 * PROPAGATION_MANDATORY
	 * 	如果已经存在一个事务，支持当前事务。如果没有一个活动的事务，则抛出异常
	 * 	单独执行b时会抛出异常
	 * 	org.springframework.transaction.IllegalTransactionStateException: 
	 * 		No existing transaction found for transaction marked with propagation 'mandatory'
	 * 	运行a，a中调用b，事务回滚
	 */
	//@Test
	public void MandatoryTest(){
		Mandatory.b();
		//Mandatory.a();
	}
	
	@Autowired Never Never;
	/**
	 * PROPAGATION_NEVER
	 */
	//@Test
	public void NeverTest(){
		Never.b();
		//Never.a();
	}
	
	//————————————————————————————————————————————————————————————————————
	/**
	 * 需要jta的支持
	 */
	@Autowired RequiredNew RequiredNew;
	/**
	 * PROPAGATION_REQUIRES_NEW 
	 * 	
	 */
	//@Test
	public void RequiredNewTest(){
		//RequiredNew.b();
		RequiredNew.a();
	}
	
	@Autowired NotSupports NotSupports;
	/**
	 * PROPAGATION_NOT_SUPPORTED  
	 * 	
	 */
	//@Test
	public void NotSupportsTest(){
		//NotSupports.b();
		NotSupports.a();
	}
	
}
