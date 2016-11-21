package org.bear.bookstore.service.impl;

import org.bear.bookstore.dao.ICustomDao;
import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.service.ICustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomServiceImpl implements ICustomService {
	@Autowired
	private ICustomDao customDao;
	
	@Override
	public void insert(Custom cus) {
		customDao.save(cus);
	}
	@Override
	public Custom select(int id) {
		return customDao.get(id);
	}

}
