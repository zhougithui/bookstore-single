package com.icitic.hrms.interaction.util;

import org.apache.commons.lang3.StringUtils;

import com.icitic.hrms.common.pojo.vo.User;


/**
 * 系统判断
 * @author zhy
 *
 */
public final class SessionUserUtils {
	public static ThreadLocal<User> THREAD_USER = new ThreadLocal<User>();
	public static ThreadLocal<User> THREAD_USER_BACK = new ThreadLocal<User>();
	
	/**
	 * 临时切换分支  与reset()配套使用
	 * @param branchId
	 */
	public static void switchToBranch(String branchId){
		THREAD_USER_BACK.set(THREAD_USER.get());
		User user = new User();
		user.setBranchID(branchId);
		THREAD_USER.set(user);
	}
	
	/**
	 * 切换回上一个分支 与switchToBranch(String branchId, String type)配套使用
	 */
	public static void reset(){
		THREAD_USER.set(THREAD_USER_BACK.get());
		THREAD_USER_BACK.set(null);
	}
	
	/**
	 * 判断是否为财务公司
	 * @return
	 */
	public static boolean isFinanceCompany(){
		if(THREAD_USER.get() != null && StringUtils.equals("1", THREAD_USER.get().getType())){
			return true;
		}
		return false;
	}
    /**
     * 判断是否为分支客户
     * @return
     */
    public static boolean isBranchCompany(){
        if(THREAD_USER.get() != null && StringUtils.equals("2", THREAD_USER.get().getType())){
            return true;
        }
        return false;
    }
	
	public static String getBranch(){
		return THREAD_USER.get() != null ? 
				THREAD_USER.get().getBranchID() : "";
	}
	
	public static String getType(){
		return THREAD_USER.get() != null ? 
				THREAD_USER.get().getType() : "";
	}
}
