package org.bear.bookstore.test.cache;

import org.bear.bookstore.domain.Custom;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomService {
	
	/**
	 * 默认key值为方法所有参数
	 * @param cusName
	 * @return
	 */
	@Cacheable(value="customCache")
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
	
	/**
	 * key  指定缓存的key是什么
	 * # 号代表这是一个 SpEL 表达式，此表达式可以遍历方法的参数对象
	 * @param cus
	 */
	@CacheEvict(value = "customCache", key = "#cus.getCusName()")
	// 清空 customCache 缓存
	public void updateAccount(Custom cus) {
		updateDB(cus);
	}

	@CacheEvict(value = "customCache", allEntries = true) // 清空 customCache 缓存
	public void reload() {
	}

	private void updateDB(Custom cus) {
		System.out.println("real update db..." + cus.getCusName());
	}
	
	/**
	 * 内容调用，不是通过proxy调用，@Cacheable、@CachePut 和 @CacheEvict 都会失效
	 */
	/*
	public Custom getAccountByName2(String userName) {
		return this.getAccountByName(userName);
	}

	@Cacheable(value = "accountCache") // 使用了一个缓存名叫 accountCache
	public Custom getAccountByName(String userName) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		return getFromDB(userName);
	}*/
}
