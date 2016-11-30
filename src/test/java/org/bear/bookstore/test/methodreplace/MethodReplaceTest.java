package org.bear.bookstore.test.methodreplace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/test/resources/spring-methodreplace.xml"}
		)
public class MethodReplaceTest {
	
	@Autowired MyValueCalculator myValueCalculator;
	
	@Test
	public void replaceTest(){
		/**
		 * 使用的是代理
		 */
		System.out.println(myValueCalculator.computeValue("a","b"));
		/**
		 * 如果是静态方法是不行的
		 */
		System.out.println(MyValueCalculator.computeValue1("aa","b"));
		/**
		 * 如果是final方法也不行
		 */
		System.out.println(myValueCalculator.computeValue3("aa","b"));
	}
}
