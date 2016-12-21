package org.bear.bookstore.web.custom;

import javax.validation.Valid;

import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.service.ICustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cus")
public class CustomController {
	@Autowired
	private ICustomService customService;
	
	@RequestMapping("/getCusById/{id}")
	public Custom queryCustom(@PathVariable int id){
		Custom cus = customService.select(id);
		return cus;
	}
	
	/**
	 * @Valid 会对Custom中需要校验的字段数据进行校验
	 * @param cus
	 * @param result 收集数据校验结果，如果有错误则封装在result中
	 * @param cookie 
	 * 		使用@CookieValue获取cookie中数据 
	 * 		还可以使用@RequestHeader("Accept-Encoding")获取header信息
	 * @return
	 */
	@RequestMapping("/getCusByName/{cusName}/{birthday}")
	public Custom queryCustomByName(@Valid Custom cus, BindingResult result, 
			@CookieValue("JSESSIONID") String cookie){
		return cus;
	}
	
}
