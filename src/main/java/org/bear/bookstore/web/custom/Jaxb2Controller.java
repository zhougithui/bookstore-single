package org.bear.bookstore.web.custom;

import org.bear.bookstore.web.vo.CustomVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Jaxb2Controller {
	@RequestMapping("/jaxb2")
	public ModelAndView jaxb2(){
		ModelAndView mv = new ModelAndView("jaxb2");
		CustomVo vo = new CustomVo();
		vo.setCusName("zh");
		mv.addObject(vo);
		return mv;
	}
}
