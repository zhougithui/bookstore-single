package org.bear.bookstore.dao.impl;

import org.bear.bookstore.dao.ICustomDao;
import org.bear.bookstore.domain.Custom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDaoImpl implements ICustomDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insert(Custom cus) {
		sessionFactory.openSession().save(cus);
	}

	@Override
	public Custom select(int id) {
		return sessionFactory.openSession().get(Custom.class, (Integer)id);
	}

}
