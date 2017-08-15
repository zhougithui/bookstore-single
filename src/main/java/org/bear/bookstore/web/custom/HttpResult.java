package org.bear.bookstore.web.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * http返回结果封装
 * <pre>
 * {
 * 	"msg":"fds",
 * 	"data":[
 * 		{
 * 			"name":"zmy"
 * 		}],
 * 	"success":false
 * }
 * </pre>
 * @author zhy
 *
 * @param <T> List<Map<String,String>>、String、Integer、Boolean
 */
public final class HttpResult<T> {
	//结果
	private T data;
	//成功标示
	private boolean success;
	//错误提示
	private String msg;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public static void main(String[] args) {
		HttpResult<List<Map<String,String>>> result = new HttpResult<>();
		result.setMsg("fds");
		result.setSuccess(false);
		List<Map<String,String>> data = new ArrayList<>();
		
		Map<String,String> map = new HashMap<>();
		map.put("name", "zmy");
		Map<String,String> map1 = new HashMap<>();
		map1.put("name", "zmy1");
		
		data.add(map);
		data.add(map1);
		
		result.setData(data);
		
		System.out.println(JSON.toJSONString(result));
		
		String jsonStr = JSON.toJSONString(result);
		result = JSON.parseObject(jsonStr, HttpResult.class);
		
		System.out.println(result.isSuccess());
		System.out.println(result.getMsg());
		
		data = result.getData();
		System.out.println(data.get(0).get("name"));
	}
}
