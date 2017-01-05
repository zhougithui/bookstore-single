package org.bear.bookstore.web.custom;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bear.bookstore.web.custom.view.XlsView;
import org.bear.bookstore.web.custom.view.XlsxStreamingView;
import org.bear.bookstore.web.custom.view.XlsxView;
import org.bear.bookstore.web.vo.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 不同excel格式以及方式导出
 * @author q
 *
 */
@Controller
public class ExcelController {

    List<Course> documents = Arrays.asList(
            new Course(1, "Spring MVC Xls View", new Date()),
            new Course(2, "Spring MVC Xlsx View", new Date()),
            new Course(3, "Spring MVC XlsxStreaming View", new Date())
    );

    @RequestMapping(value="/xls",method = RequestMethod.GET)
    public ModelAndView getDocumentsXls(Model model) {
        model.addAttribute("courses", documents);
        ModelAndView mv = new ModelAndView();
		mv.setView(new XlsView());
        return mv;
    }
    
    
    @RequestMapping(value="/xlsx",method = RequestMethod.GET)
    public ModelAndView getDocumentsXlsx(Model model) {
        model.addAttribute("courses", documents);
        ModelAndView mv = new ModelAndView();
		mv.setView(new XlsxView());
        return mv;
    }
    
    @RequestMapping(value="/xlsxs",method = RequestMethod.GET)
    public ModelAndView getDocumentsXlsxStream(Model model) {
    	model.addAttribute("courses", documents);
    	ModelAndView mv = new ModelAndView();
    	mv.setView(new XlsxStreamingView());
    	return mv;
    }
}