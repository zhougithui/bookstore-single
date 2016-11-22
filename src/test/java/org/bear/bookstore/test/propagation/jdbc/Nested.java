package org.bear.bookstore.test.propagation.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Nested {
	@Autowired JdbcTemplate jdbcTemplate;
	
	@Transactional(
			propagation=Propagation.REQUIRED,
			rollbackFor={Exception.class}
			)
	public void a(){
		jdbcTemplate.execute("insert into custom(address,cusname,email,phone,sex) values('北京市，朝阳区，十里河村','zhouhui','zh@qq.com','15555555555','1')");
		
		b();
	}
	
	@Transactional(
			propagation=Propagation.NESTED,
			rollbackFor={Exception.class}
			)
	public void b(){
		jdbcTemplate.execute("insert into custom(address,cusname,email,phone,sex) values('北京市，朝阳区，十里河村','zmy','zmy@qq.com','15555555555','0')");
		
		/*int x = 1/0;
		System.out.println(x);*/
	}
	
}
