package org.bear.bookstore.test.cache;

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
	@Autowired CustomService cusService;
	
	/**
	 * first query...
	 * second query...
	 * 2016-11-24 17:09:57.837 [main] ERROR org.bear.bookstore.test.cache.CustomService at ? - real query custom......
	 * 2016-11-24 17:09:57.837 [main] ERROR org.bear.bookstore.test.cache.CustomService at ? - query custom from db......
	 */
	@Test
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
}
