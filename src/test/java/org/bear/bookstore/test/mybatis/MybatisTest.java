package org.bear.bookstore.test.mybatis;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bear.bookstore.test.mybatis.bean.Custom;
import org.bear.bookstore.test.mybatis.mapper.CustomMapper;

public class MybatisTest {
	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.addLoadedResource("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(MybatisTest.class.getResourceAsStream("mybatis-config.xml"));
		
		CustomMapper customMapper = sqlSessionFactory.openSession().getMapper(CustomMapper.class);
		Custom cus = new Custom();
		cus.setCusName("xxxxx");
		customMapper.save(cus);
		
		System.out.println(customMapper.select(3).getCusName());
	}
}
