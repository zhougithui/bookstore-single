package org.bear.bookstore.integrations.example2.web;

import java.util.List;

import org.bear.bookstore.integrations.example2.domain.User;
import org.bear.bookstore.integrations.example2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@Autowired UserService userService;
	
	/**
	 * consumes： 指定请求参数
	 * produces：执行返回结果类型
	 * @return
	 */
	@RequestMapping(value="/ulist", 
			consumes={"application/json;charset=UTF-8"},
			produces={"application/json;charset=UTF-8"},
			method=RequestMethod.POST)
	@ResponseBody
	public List<User> queryList(){
		return userService.findUsers();
	}
	
	@RequestMapping(value="/preSave")
	public String preSave(){
		return "addUser";
	}
	
	@RequestMapping(value="/saveu")
	@ResponseBody
	public User saveU(User user){
		return user;
	}
}
