package org.bear.bookstore.test.cache;

import org.bear.bookstore.domain.Custom;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomService2 {
	
	public Custom getCustom(String cusName){
		System.out.println("real query custom......" + cusName);
		return getFromDB(cusName);
	}

	private Custom getFromDB(String cusName) {
		System.out.println("query custom from db......" + cusName);
		Custom cus = new Custom();
		cus.setCusName(cusName);
		return cus;
	}
	
	public void updateAccount(Custom cus) {
		updateDB(cus);
	}

	public void reload() {
	}

	private void updateDB(Custom cus) {
		System.out.println("real update db..." + cus.getCusName());
	}
}
