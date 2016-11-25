package org.bear.bookstore.test.cache;

import java.util.ArrayList;
import java.util.List;

import org.bear.bookstore.domain.Custom;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomListService {
	/**
	 * 使用org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean
	 * @param keys
	 * @return
	 */
	@Cacheable(value="customCache", cacheManager="simpleCacheManager")
	public List<Custom> getCusList(List<Custom> keys){
		System.out.println("get from db...");
		return new ArrayList<>();
	}
	
	/**
	 * 使用ehcache的org.springframework.cache.ehcache.EhCacheManagerFactoryBean
	 * ehCustomCache在ehcache.xml中配置
	 * @param keys
	 * @return
	 */
	@Cacheable(value="ehCustomCache", cacheManager="ehCacheManager")
	public List<Custom> getCusList1(List<Custom> keys){
		System.out.println("get from db...");
		return new ArrayList<>();
	}
	
}
