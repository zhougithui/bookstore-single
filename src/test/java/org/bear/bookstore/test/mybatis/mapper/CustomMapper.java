package org.bear.bookstore.test.mybatis.mapper;

import org.bear.bookstore.test.mybatis.bean.Custom;

public interface CustomMapper {
	public void save(Custom cus);
	
	public Custom select(Integer id);
}
