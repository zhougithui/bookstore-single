package org.bear.bookstore.web.jms;

import org.bear.bookstore.domain.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jms")
@Lazy(true)
public class JmsController {
	@Autowired
	@Qualifier("jmsCustomService")
	private ICustomService customService;
	
	@RequestMapping("/getById/{id}")
	@ResponseBody
	public Custom queryCustom(@PathVariable int id){
		Custom cus = customService.select(id);
		return cus;
	}
}
