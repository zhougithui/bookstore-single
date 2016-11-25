package org.bear.bookstore.test.cache;

import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;

import org.bear.bookstore.domain.Custom;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Spring			JSR-107			Remark
	@Cacheable 		@CacheResult	Fairly similar. @CacheResult can cache specific exceptions and force the execution of the method regardless of the content of the cache.
	@CachePut		@CachePut		While Spring updates the cache with the result of the method invocation, JCache requires to pass it as an argument that is annotated with @CacheValue. Due to this difference, JCache allows to update the cache before or after the actual method invocation.
	@CacheEvict		@CacheRemove	Fairly similar. @CacheRemove supports a conditional evict in case the method invocation results in an exception.
	@CacheEvict(allEntries=true)	@CacheRemoveAll	See @CacheRemove.
	@CacheConfig	@CacheDefaults	Allows to configure the same concepts, in a similar fashion.
 * @author q
 *
 */
@Service
public class SpringVsJsr107Annotation {
	/**
	 * 默认key值为方法所有参数
	 * @param cusName
	 * @return
	 */
	//@Cacheable(value="customCache")
	@CacheResult(cacheName="customCache")
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
	//@CacheEvict(value = "customCache", key = "#cus.getCusName()")
	@CacheRemove(cacheName="customCache")
	// 清空 customCache 缓存
	public void updateAccount(Custom cus) {
		updateDB(cus);
	}

	//@CacheEvict(value = "customCache", allEntries = true) // 清空 customCache 缓存
	@CacheRemoveAll(cacheName="customCache")
	public void reload() {
	}

	private void updateDB(Custom cus) {
		System.out.println("real update db..." + cus.getCusName());
	}
}
