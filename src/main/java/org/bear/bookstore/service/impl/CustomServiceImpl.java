package org.bear.bookstore.service.impl;

import org.bear.bookstore.common.annotation.DbKey;
import org.bear.bookstore.common.aware.ProxyReferenceAware;
import org.bear.bookstore.dao.ICustomDao;
import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.service.ICustomService;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("customService")
public class CustomServiceImpl implements ICustomService, ProxyReferenceAware {
	@Autowired
	private ICustomDao customDao;
	
	@Value("十里河,zh")
	private Custom custom;
	
	@Override
	public void insert(Custom cus) {
		customDao.save(cus);
	}
	
	@DbKey("testDbKey")
	@Override
	public Custom select(int id) {
		Custom cus = customDao.get(id);
		if(cus == null){
			return custom;
		}
		return cus;
	}

	ICustomService customService;
	@Override
	public void setProxyReferece(Object proxy) {
		System.out.println(this.getClass().getName() + " is proxy " + AopUtils.isAopProxy(proxy));
		this.customService = (ICustomService) proxy;
	}

}
