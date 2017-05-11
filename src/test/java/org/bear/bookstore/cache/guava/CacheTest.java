package org.bear.bookstore.cache.guava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class CacheTest {
	public static void main(String[] args) {
		/**
		 * 在build Cache时，传入一个CacheLoader用来加载缓存，操作流程如下。
			1．应用业务代码直接调用getCache.get(sortId)。
			2．首先查询Cache，如果缓存中有，则直接返回缓存数据。
			3．如果缓存没有命中，则委托给CacheLoader，CacheLoader会回源到SoR查询源数据（返回值必须不为null，可以包装为Null对象），然后写入缓存。
			
			使用CacheLoader后有几个好处。
			●     应用业务代码更简洁了，不需要像Cache-Aside模式那样缓存查询代码和SoR代码交织在一起。如果缓存使用逻辑散落在多处，则使用这种方式很简单的消除了重复代码。
			●     解决Dog-pile effect，即当某个缓存失效时，又有大量相同的请求没命中缓存，从而同时请求到后端，导致后端压力太大，此时限制一个请求去拿即可。
		 */
		LoadingCache<Integer,Result<Category>> getCache =
			       CacheBuilder.newBuilder()
			               .softValues()
			               .maximumSize(5000).expireAfterWrite(2, TimeUnit.MINUTES)
			                .build(new CacheLoader<Integer,Result<Category>>() {
			                   @Override
			                   public Result<Category> load(final Integer sortId) throws Exception {
			                        //return categoryService.get(sortId);
			                	   System.out.println("get resource from db");
			                	   return new Result<>();
			                   }
			               });
		for(int i=0; i<10; i++){
			new Thread(()->{
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println(getCache.get(1));
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}
	}
}
