package org.bear.bookstore.web.custom;

import javax.validation.Valid;

import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.service.ICustomService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/getCusByName/{name}")
	public Custom queryCustomByName(@Valid Custom cus){
		return cus;
	}
}
