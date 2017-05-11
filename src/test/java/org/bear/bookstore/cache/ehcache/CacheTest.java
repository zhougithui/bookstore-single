package org.bear.bookstore.cache.ehcache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.PooledExecutionServiceConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.WriteBehindConfigurationBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.spi.loaderwriter.BulkCacheLoadingException;
import org.ehcache.spi.loaderwriter.BulkCacheWritingException;
import org.ehcache.spi.loaderwriter.CacheLoaderWriter;


/**
 * Read-Through
 * Read-Through，业务代码首先调用Cache，如果Cache不命中由Cache回源到SoR，
 * 而不是业务代码（即由Cache读SoR）。使用Read-Through模式，
 * 需要配置一个CacheLoader组件用来回源到SoR加载源数据。
 * Guava Cache和Ehcache 3.x都支持该模式。
 * 
 * 
 * Write-Through
 * Write-Through，称之为穿透写模式/直写模式，业务代码首先调用Cache写（新增/修改）数据，
 * 然后由Cache负责写缓存和写SoR，而不是业务代码。
 * 使用Write-Through模式需要配置一个CacheWriter组件用来回写SoR。Guava Cache没有提供支持。
 * Ehcache 3.x支持该模式。
 * Ehcache需要配置一个CacheLoaderWriter，CacheLoaderWriter知道如何去写SoR。
 * 当Cache需要写（新增/修改）数据时，首先调用CacheLoaderWriter来同步（立即）到SoR，成功后会更新缓存。
 * 
 * 
 * Write-Behind
 * Write-Behind，也叫Write-Back，称之为回写模式，
 * 不同于Write-Through是同步写SoR和Cache，Write-Behind是异步写。
 * 异步之后可以实现批量写、合并写、延时和限流。
 * @author q
 *
 */
public class CacheTest {
	public static void main(String[] args) {
		/**
		 * 	Ehcache 3.x使用CacheLoaderWriter来实现，通过load(K key)和
		 * 	loadAll(Iterable<? extends K> keys)分别来加载单个KEY和批量KEY。
		 * 	Ehcache 3.1没有自己去解决Dog-pile effect。
		 */
		/*CacheManager cacheManager = CacheManagerBuilder. newCacheManagerBuilder(). build(true);
		org.ehcache.Cache<String, String> myCache =cacheManager. createCache ("myCache",
		       CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,String.class,
		               ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100,MemoryUnit.MB))
		               .withDispatcherConcurrency(4)
		               .withExpiry(Expirations.timeToLiveExpiration(Duration.of(10,TimeUnit.SECONDS)))
		                .withLoaderWriter(new CacheLoaderWriter<String, String> () {
		                   @Override
		                   public String load(String key) throws Exception {
		                        //return readDB(key);
		                	   System.out.println("read from db");
		                	   return "fdsa";
		                   }
		                    @Override
		                   public Map<String, String> loadAll(Iterable<? extends String> keys) throws BulkCacheLoadingException, Exception {
		                        return null;
		                   }
							@Override
							public void write(String key, String value) throws Exception {
								
							}
							@Override
							public void writeAll(Iterable<? extends Entry<? extends String, ? extends String>> entries)
									throws BulkCacheWritingException, Exception {
								
							}
							@Override
							public void delete(String key) throws Exception {
								
							}
							@Override
							public void deleteAll(Iterable<? extends String> keys)
									throws BulkCacheWritingException, Exception {
								
							}
		               }));*/
	
	
	
		/**
		 * Ehcache 3.x还是使用CacheLoaderWriter来实现，
		 * 通过write(String key, String value)、
		 * writeAll(Iterable<? extends Map.Entry<? extends String, ?extends String>> entries)
		 * 和delete(String key)、deleteAll(Iterable<? extends String> keys)
		 * 分别来支持单个写、批量写和单个删除、批量删除操作。
				操作流程如下。
				1．当我们调用myCache.put("e","123")或者myCache.putAll(map)时，写缓存。
				2．首先，Cache会将写操作立即委托给CacheLoaderWriter#write和#writeAll，然后由CacheLoaderWriter负责立即去写SoR。
				3．当写SoR成功后，再写入Cache。
		 */
		/*CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
		org.ehcache.Cache<String, String> myCache =cacheManager.createCache ("myCache",
		       CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,String.class,
		               ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100,MemoryUnit.MB))
		               .withDispatcherConcurrency(4)
		               .withExpiry(Expirations.timeToLiveExpiration(Duration.of(10,TimeUnit.SECONDS)))
		                .withLoaderWriter(new CacheLoaderWriter<String, String> () {
		                   @Override
		                   public void write(String key, String value) throws Exception{
		                        //write
		                   }
		                   @Override
		                   public void writeAll(Iterable<? extends Map.Entry<? extends String, ? extends String>> entries) throws BulkCacheWritingException,Exception {
		                        for(Object entry: entries) {
		                            //batch write
		                        }
		                   }
		                   @Override
		                    public void delete(String key) throws Exception {
		                        //delete
		                   }
		                   @Override
		                   public void deleteAll(Iterable<? extends String>keys) throws BulkCacheWritingException, Exception {
		                        for(Object key :keys) {
		                            //batch delete
		                        }
		                   }
						@Override
						public String load(String key) throws Exception {
							// TODO Auto-generated method stub
							return null;
						}
						@Override
						public Map<String, String> loadAll(Iterable<? extends String> keys)
								throws BulkCacheLoadingException, Exception {
							// TODO Auto-generated method stub
							return null;
						}
		               }).build());*/
		
		
		/**
		 * 几个重要配置如下。
			●     ThreadPool：使用PooledExecutionServiceConfigurationBuilder配置线程池；然后WriteBehindConfigurationBuilder通过useThreadPool配置使用哪一个线程池；
			●     WriteBehindConfigurationBuilder：配置WriteBehind策略；
			●     CacheLoaderWriter：配置WriteBehind如何操作SoR。
			WriteBehindConfigurationBuilder会进行如下几个配置。
			●     newUnBatchedWriteBehindConfiguration：表示不进行批量处理，那么所有批量操作都将会转换成单个操作，即CacheLoaderWriter只需要实现write和delete即可。
			●     queueSize(int size)：因为操作是异步回写SoR，需要将操作先放入写操作等待队列，因此，使用queue size定义写操作等待队列最大大小，即线程池队列大小。内部使用NonBatchingLocalHeapWriteBehindQueue。
			●    concurrencyLevel(int concurrency)：配置使用多少个并发线程和队列进行WriteBehind。因为我们只传入一个线程池，这是如何实现该模式的呢？
		 */
		CacheManager cacheManager = CacheManagerBuilder. newCacheManagerBuilder()
			       .using(PooledExecutionServiceConfigurationBuilder
			               .newPooledExecutionServiceConfigurationBuilder()
			               .pool("writeBehindPool", 1, 5)
			               .build())
			       .build(true);
			org.ehcache.Cache<String, String> myCache =cacheManager.createCache("myCache",
			       CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,String.class,
			               ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100,MemoryUnit.MB))
			               .withDispatcherConcurrency(4)
			               .withExpiry(Expirations.timeToLiveExpiration(Duration.of(10,TimeUnit.SECONDS)))
			                .withLoaderWriter(new CacheLoaderWriter<String,String >() {
			                   @Override
			                   public void write(String key, String value) throws Exception{
			                        //write
			                    }
			 
			                   @Override
			                   public void delete(String key) throws Exception {
			                        //delete
			                    }

							@Override
							public String load(String key) throws Exception {
								// TODO Auto-generated method stub
								return null;
							}

							@Override
							public Map<String, String> loadAll(Iterable<? extends String> keys)
									throws BulkCacheLoadingException, Exception {
								// TODO Auto-generated method stub
								return null;
							}

							@Override
							public void writeAll(Iterable<? extends Entry<? extends String, ? extends String>> entries)
									throws BulkCacheWritingException, Exception {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void deleteAll(Iterable<? extends String> keys)
									throws BulkCacheWritingException, Exception {
								// TODO Auto-generated method stub
								
							} 
			               })
			               .add(WriteBehindConfigurationBuilder
			                        .newUnBatchedWriteBehindConfiguration()
			                        .queueSize(5)
			                        .concurrencyLevel(2)
			                        .useThreadPool("writeBehindPool")
			                        .build()));
	}
}
