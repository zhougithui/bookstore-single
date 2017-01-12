package org.bear.bookstore.web.jaxws;

import org.bear.bookstore.domain.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jaxws")
@Lazy(true)
public class JaxWsController {
	@Autowired
	@Qualifier("jaxCustomService")
	private ICustomService jaxCustomService;
	
	@RequestMapping("/getById/{id}")
	@ResponseBody
	public Custom queryCustom(@PathVariable int id){
		Custom cus = jaxCustomService.select(id);
		return cus;
	}
}
