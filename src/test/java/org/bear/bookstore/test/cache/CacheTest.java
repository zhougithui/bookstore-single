package org.bear.bookstore.test.cache;

import java.util.ArrayList;
import java.util.List;

import org.bear.bookstore.domain.Custom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author q
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/test/resources/spring-cache.xml"}
		)
public class CacheTest {
	//两种方式都可以实现，个人觉得注解方式更加醒目，犯错概率会小点
	/**
	 * 注解方式缓存
	 */
	//@Autowired CustomService cusService;
	/**
	 * 配置方式缓存
	 */
	@Autowired CustomService2 cusService;
	
	/**
	 * first query...
	 * second query...
	 * 2016-11-24 17:09:57.837 [main] ERROR org.bear.bookstore.test.cache.CustomService at ? - real query custom......
	 * 2016-11-24 17:09:57.837 [main] ERROR org.bear.bookstore.test.cache.CustomService at ? - query custom from db......
	 */
	//@Test
	public void cusTest(){
		// 第一次查询，应该走数据库
	     /*System.out.println("first query..."); 
	     cusService.getCustom("c1"); 
	     // 第二次查询，应该不查数据库，直接返回缓存的值
	     System.out.println("second query..."); 
	     cusService.getCustom("c1");
	     System.out.println(); */
	     
	     
		/*start testing clear cache...
		real query custom......c1
		query custom from db......c1
		real query custom......c2
		query custom from db......c2
		
		real update db...c1
		
		real query custom......c1
		query custom from db......c1
		
		real query custom......c1
		query custom from db......c1
		real query custom......c2
		query custom from db......c2*/
	     System.out.println("start testing clear cache...");   
	     //更新某个记录的缓存，首先构造两个账号记录，然后记录到缓存中
	     Custom c1 = cusService.getCustom("c1");
	     Custom c2 = cusService.getCustom("c2");
	     
	     // 开始更新其中一个   
	     c1.setId(1);
	     cusService.updateAccount(c1); 
	     cusService.getCustom("c1");// 因为被更新了，所以会查询数据库    
	     cusService.getCustom("c2");// 没有更新过，应该走缓存    
	     cusService.getCustom("c1");// 再次查询，应该走缓存    
	     // 更新所有缓存
	     cusService.reload(); 
	     cusService.getCustom("c1");// 应该会查询数据库    
	     cusService.getCustom("c2");// 应该会查询数据库   
	     cusService.getCustom("c1");// 应该走缓存    
	     cusService.getCustom("c2");// 应该走缓存
	}
	
	/**
	 * spring对jsr107注解的支持，需要jcache-api包和spring-context-support包
	 */
	@Test
	public void testJsr107(){
		 System.out.println("start testing clear cache...");   
	     //更新某个记录的缓存，首先构造两个账号记录，然后记录到缓存中
	     Custom c1 = cusService.getCustom("c1");
	     Custom c2 = cusService.getCustom("c2");
	     
	     // 开始更新其中一个   
	     c1.setId(1);
	     cusService.updateAccount(c1); 
	     cusService.getCustom("c1");// 因为被更新了，所以会查询数据库    
	     cusService.getCustom("c2");// 没有更新过，应该走缓存    
	     cusService.getCustom("c1");// 再次查询，应该走缓存    
	     // 更新所有缓存
	     cusService.reload(); 
	     cusService.getCustom("c1");// 应该会查询数据库    
	     cusService.getCustom("c2");// 应该会查询数据库   
	     cusService.getCustom("c1");// 应该走缓存    
	     cusService.getCustom("c2");// 应该走缓存
	}
	
	@Autowired CustomListService s;
	//@Test
	public void listTest(){
		List<Custom> keys = new ArrayList<>();
		for(int i=0;i<10;i++){
			Custom c = new Custom();
			c.setAddress("a" + i);
			
			keys.add(c);
		}
		/**
		 * 默认是以org.springframework.aop.framework.AdvisedSupport.MethodCacheKey作为键值，包含method和method.hashcode
		 * 首先从缓存里面取值，如果没有则放入缓存中，如果有则直接放回不执行方法的真正调用
		 */
		s.getCusList(keys );
		s.getCusList(keys );
		//改变key值
		keys.get(0).setCusName("ffdas");
		s.getCusList(keys );
		System.out.println("^^^^^^^^^^^^^缓存放入ehcache^^^^^^^^^^^^^^^^^^^^^^^^");
		s.getCusList1(keys);
		s.getCusList1(keys);
		keys.get(0).setCusName("ffdas");
		s.getCusList1(keys);
	}
}
