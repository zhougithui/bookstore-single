package org.bear.bookstore.dao.impl;

import org.bear.bookstore.common.dao.BookStoreBaseDaoImpl;
import org.bear.bookstore.dao.ICustomDao;
import org.bear.bookstore.domain.Custom;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDaoImpl extends BookStoreBaseDaoImpl<Custom> implements ICustomDao {
	
}
