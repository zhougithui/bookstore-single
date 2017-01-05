package org.bear.bookstore.web.custom;

import java.util.Arrays;

import org.bear.bookstore.web.custom.view.ExcelView;
import org.bear.bookstore.web.custom.view.PDFView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExcelAndPdfController {
	
	@RequestMapping("/excel")
	public ModelAndView excel(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("wordList", Arrays.asList("Hello", "Spring", "Framework"));
		mv.setView(new ExcelView());
		return mv;
	}
	
	
	@RequestMapping("/pdf")
	public ModelAndView pdf(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("wordList", Arrays.asList("Hello", "Spring", "Framework"));
		mv.setView(new PDFView());
		return mv;
	}
}
