package com.icitic.hrms.interaction.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.icitic.hrms.branch.BranchConstans;

/**
 * 对token进行管理，保存从服务器端获取的token信息
 */
public final class TokenManager {
	private static Map<String, TokenInfo> BRANCH_TOKEM_MAP = new ConcurrentHashMap<String, TokenInfo>();
	private static Object lock = new Object();

    /**
     * 初始化token
     */
	public static void initTokenIfNotExists(){
        //判断若branch对应的token为空
		if(BRANCH_TOKEM_MAP.get(SessionUserUtils.getBranch()) == null){
            //锁，防止多次初始化
			synchronized(lock){
                //如果token为空
				if(BRANCH_TOKEM_MAP.get(SessionUserUtils.getBranch()) == null){
					BRANCH_TOKEM_MAP.put(SessionUserUtils.getBranch(), new TokenManager().new TokenInfo());
                    //调用getTokenRemote方法获取token
					getTokenRemote();
				}
			}
		}
	}
	//2个小时过期时间
	static long TWO_HOUR = 2l * 60l * 60l * 1000l;
	/**
	 * 刷新服务器和本地token信息
	 * @return
	 */
	static synchronized String getTokenRemote(){
		//当前时间
		long currTime = System.currentTimeMillis();
		TokenInfo tokenInfo = BRANCH_TOKEM_MAP.get(SessionUserUtils.getBranch());
		long lastTime = tokenInfo.getTokenUpdateTimeStamp();
        /**
         * 如果初始化或者过期则远程获取token，并且更新token以及保存token更新时间节点
         * 1、lastTime为0表示第一次进行初始化
         * 2、当前时间减去上一次更新的时间节点lastTime获取时间间隔，如果超过2小时（过期时间），则重新获取
         * 防止同一时间多次刷新token，导致请求失效
         */
		if(lastTime == 0L || ((currTime - lastTime) >= TWO_HOUR)){
			Map<String, Object> parameters = new HashMap<String, Object>();
            //获取token的固定参数，qppid和secret，value由业务系统提供，为固定值
            parameters.put("appid", BranchConstans.APPID);
            parameters.put("secret", BranchConstans.SECRET);
            //获取token
            String tokenJsonStr = HttpClientUtil.postHttp("http://192.168.43.248:8080/middle/token/getToken", parameters);
            //判断若token非空
			if(StringUtils.isNotBlank(tokenJsonStr)){
                //更新内存中token以及保存token更新时间节点
				tokenInfo.setAccessToken((String) HttpClientUtil.toHashMap(tokenJsonStr).get("access_token"));
				tokenInfo.setTokenUpdateTimeStamp(System.currentTimeMillis());
				
				BRANCH_TOKEM_MAP.put(SessionUserUtils.getBranch(), tokenInfo);
			}
            //打印token值，测试使用，可去掉
			System.out.println("获取的token值为：" + tokenJsonStr);
			return tokenJsonStr;
		}
		
		return tokenInfo.getAccessToken();
	}
	//返回上次更新的token
	static String getAccessToken(){
		return BRANCH_TOKEM_MAP.get(SessionUserUtils.getBranch()).getAccessToken();
	}
	
	
	/**
	 * token信息，token和token更新时间戳
	 * @author q
	 *
	 */
	private class TokenInfo{
		private String accessToken;
		private long tokenUpdateTimeStamp;
		public String getAccessToken() {
			return accessToken;
		}
		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}
		public long getTokenUpdateTimeStamp() {
			return tokenUpdateTimeStamp;
		}
		public void setTokenUpdateTimeStamp(long tokenUpdateTimeStamp) {
			this.tokenUpdateTimeStamp = tokenUpdateTimeStamp;
		}
		
	}
}
