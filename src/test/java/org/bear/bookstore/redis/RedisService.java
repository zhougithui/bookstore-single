package org.bear.bookstore.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

/**
 * redis 缓存操作
 * 
 * @author zhou.hui 2017-08-10 15:35:44
 */
public class RedisService implements InitializingBean {
	
    /**
	 * redis hash操作
	 * @author zhou.hui 2017-08-11 09:31:42
	 */
	public class HashService {
		
		HashOperations<String, String, String> hashOps;
		
		HashService(){
			hashOps = redisTemplate.opsForHash();
		}
		
	}


	/**
	 * redis list操作
	 * @author zhou.hui 2017-08-11 09:22:28
	 */
	public class ListService {
		private ListOperations<String, String> listOps;
		
		ListService(){
			listOps = redisTemplate.opsForList();
		}
		
	    public boolean removeList(String k) {
	        return remove(KEY_PREFIX_LIST + k);
	    }

	    /**
	     * list缓存
	     * @param k
	     * @param v
	     * @param time
	     * @return
	     */
	    public boolean cacheList(String k, String v, long time) {
	        String key = KEY_PREFIX_LIST + k;
	        try {
	            listOps.rightPush(key, v);
	            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
	            return true;
	        } catch (Throwable t) {
	            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
	        }
	        return false;
	    }

	    /**
	     * 缓存list
	     * @param k
	     * @param v
	     * @return
	     */
	    public boolean cacheList(String k, String v) {
	        return cacheList(k, v, -1);
	    }

	    /**
	     * 缓存list
	     * @param k
	     * @param v
	     * @param time
	     * @return
	     */
	    public boolean cacheList(String k, List<String> v, long time) {
	        String key = KEY_PREFIX_LIST + k;
	        try {
	            listOps.rightPushAll(key, v);
	            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
	            return true;
	        } catch (Throwable t) {
	            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
	        }
	        return false;
	    }

	    /**
	     * 缓存list
	     * @param k
	     * @param v
	     * @return
	     */
	    public boolean cacheList(String k, List<String> v) {
	       return cacheList(k, v, -1);
	    }

	    /**
	     * 获取list缓存
	     * @param k
	     * @param start
	     * @param end
	     * @return
	     */
	    public List<String> getList(String k, long start, long end) {
	        try {
	            return listOps.range(KEY_PREFIX_LIST + k, start, end);
	        } catch (Throwable t) {
	            logger.error("获取list缓存失败key[" + KEY_PREFIX_LIST + k + ", error[" + t + "]");
	        }
	        return null;
	    }

	    /**
	     * 获取总条数, 可用于分页
	     * @param listOps
	     * @param k
	     * @return
	     */
	    public long getListSize(String k) {
	        try {
	            return listOps.size(KEY_PREFIX_LIST + k);
	        } catch (Throwable t) {
	            logger.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], error[" + t + "]");
	        }
	        return 0;
	    }

	    /**
	     * 移除list缓存
	     * @param k
	     * @return
	     */
	    public boolean removeOneOfList(String k) {
	        String key = KEY_PREFIX_LIST + k;
	        try {
	            listOps.rightPop(key);
	            return true;
	        } catch (Throwable t) {
	            logger.error("移除list缓存失败key[" + KEY_PREFIX_LIST + k + ", error[" + t + "]");
	        }
	        return false;
	    }
	    
	    /**
	     * 判断缓存是否存在
	     * @param k
	     * @return
	     */
	    public boolean containsListKey(String k) {
	    	return containsKey(KEY_PREFIX_LIST + k);
	    }
	    
	}


	/**
	 * redis set 操作
	 * @author zhou.hui 2017-08-11 09:21:02
	 */
	public class SetService {
		private SetOperations<String, String> setOps;
		
		SetService(){
			setOps = redisTemplate.opsForSet();
		}
		
	    public boolean removeSet(String k) {
	        return remove(KEY_PREFIX_SET + k);
	    }
	    
	    /**
	     * 缓存set操作
	     * @param k
	     * @param v
	     * @param time
	     * @return
	     */
	    public boolean cacheSet(String k, String v, long time) {
	        String key = KEY_PREFIX_SET + k;
	        try {
	            setOps.add(key, v);
	            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
	            return true;
	        } catch (Throwable t) {
	            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
	        }
	        return false;
	    }

	    /**
	     * 缓存set
	     * @param k
	     * @param v
	     * @return
	     */
	    public boolean cacheSet(String k, String v) {
	        return cacheSet(k, v, -1);
	    }

	    /**
	     * 缓存set
	     * @param k
	     * @param v
	     * @param time
	     * @return
	     */
	    public boolean cacheSet(String k, Set<String> v, long time) {
	        String key = KEY_PREFIX_SET + k;
	        try {
	            setOps.add(key, v.toArray(new String[v.size()]));
	            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
	            return true;
	        } catch (Throwable t) {
	            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
	        }
	        return false;
	    }

	    /**
	     * 缓存set
	     * @param k
	     * @param v
	     * @return
	     */
	    public boolean cacheSet(String k, Set<String> v) {
	        return cacheSet(k, v, -1);
	    }

	    /**
	     * 获取缓存set数据
	     * @param k
	     * @return
	     */
	    public Set<String> getSet(String k) {
	        try {
	            return setOps.members(KEY_PREFIX_SET + k);
	        } catch (Throwable t) {
	            logger.error("获取set缓存失败key[" + KEY_PREFIX_SET + k + ", error[" + t + "]");
	        }
	        return null;
	    }

	    /**
	     * 判断缓存是否存在
	     * @param k
	     * @return
	     */
	    public boolean containsSetKey(String k) {
	    	return containsKey(KEY_PREFIX_SET + k);
	    }
	    
	}


	/**
	 * redis string 操作
	 * @author zhou.hui 2017-08-11 09:18:52
	 */
	public class StringService {
		
		private ValueOperations<String, String> valueOps;
		
		StringService(){
			valueOps =  redisTemplate.opsForValue();
		}
	    /**
	     * 缓存value操作
	     * @param k
	     * @param v
	     * @param time TimeUnit.SECONDS
	     * @return
	     */
	    public boolean cacheValue(String k, String v, long time) {
	        String key = KEY_PREFIX_VALUE + k;
	        try {
	            valueOps.set(key, v);
	            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
	            return true;
	        } catch (Throwable t) {
	            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
	        }
	        return false;
	    }

	    /**
	     * 缓存value操作
	     * @param k
	     * @param v
	     * @return
	     */
	    public boolean cacheValue(String k, String v) {
	        return cacheValue(k, v, -1);
	    }

	    /**
	     * 获取缓存
	     * @param k
	     * @return
	     */
	    public String getValue(String k) {
	        try {
	            return valueOps.get(KEY_PREFIX_VALUE + k);
	        } catch (Throwable t) {
	            logger.error("获取缓存失败key[" + KEY_PREFIX_VALUE + k + ", error[" + t + "]");
	        }
	        return null;
	    }

	    /**
	     * 移除缓存
	     * @param k
	     * @return
	     */
	    public boolean removeValue(String k) {
	        return remove(KEY_PREFIX_VALUE + k);
	    }
	    
	    /**
	     * 判断缓存是否存在
	     * @param k
	     * @return
	     */
	    public boolean containsValueKey(String k) {
	    	return containsKey(KEY_PREFIX_VALUE + k);
	    }
	    
	}


	/**
     * 日志记录
     */
    private Logger logger = LoggerFactory.getLogger(RedisService.class);
    
    private RedisTemplate<String, String> redisTemplate;
    
    private StringService strService;
    private SetService setService;
    private ListService listService;
    private HashService hashService;
    
    /**
     * 前缀
     */
    private static final String KEY_PREFIX_VALUE = "ec.strs.";
    private static final String KEY_PREFIX_SET = "ec.sets.";
    private static final String KEY_PREFIX_LIST = "ec.list.";

    public RedisService(RedisTemplate<String, String> redisTemplate){
    	this.redisTemplate = redisTemplate;
    }
    
    /**
     * str相关操作
     */
    public StringService strService(){
    	return strService;
    }
    
    /**
     * sets相关操作
     */
    public SetService setService(){
    	return setService;
    }
    
    /**
     * list相关操作
     */
    public ListService listService(){
    	return listService;
    }
    
    /**
     * hash相关操作
     */
    public HashService hashService(){
    	return hashService;
    }
    
    //--------------------工具方法----------------------------------
    
    /**
     * 获取随机key
     */
    public String randomKey(){
    	return redisTemplate.randomKey();
    }
    
    /**
     * 检测服务心跳
     */
    public String ping(){
    	String ping = null;
    	
		try {
			ping = redisTemplate.execute(new RedisCallback<String>() {

				@Override
				public String doInRedis(RedisConnection conn) throws DataAccessException {
					return conn.ping();
				}
			});
		} catch (Exception e) {
			logger.error("与redis服务器断开连接！！！");
		}
    	
    	return ping;
    }
    
    private boolean containsKey(String key) {
    	try {
    		return redisTemplate.hasKey(key);
    	} catch (Throwable t) {
    		logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
    	}
    	return false;
    }
    

    /**
     * 移除缓存
     * @param key
     * @return
     */
    private boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

	/**
	 * 初始化redis服务
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		this.strService = new StringService();
		this.listService = new ListService();
		this.setService = new SetService();
		this.hashService = new HashService();
	}
}