package org.bear.bookstore.test.mybatis;

import org.bear.bookstore.test.mybatis.bean.Custom;
import org.bear.bookstore.test.mybatis.mapper.CustomMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisSpringTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml", MybatisSpringTest.class);
		ac.registerShutdownHook();
		
		CustomMapper customMapper = ac.getBean(CustomMapper.class);
		
		Custom cus = new Custom();
		cus.setCusName("xxxxx");
		customMapper.save(cus);
		
		System.out.println(customMapper.select(3).getCusName());
		
		ac.close();
	}
}
