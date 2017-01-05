package org.bear.bookstore.web.rss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RssController {

	@RequestMapping(value="/rssfeed", method = RequestMethod.GET)
	public ModelAndView getFeedInRss() {

		List<SampleContent> items = new ArrayList<SampleContent>();
		
		SampleContent content  = new SampleContent();
		content.setTitle("bear_hui的博客1");
		content.setUrl("http://blog.csdn.net/zhou_java_hui");
		content.setSummary("博客列表1");
		content.setCreatedDate(new Date());
		items.add(content);
		
		SampleContent ct  = new SampleContent();
		ct.setTitle("bear_hui的博客2");
		ct.setUrl("http://blog.csdn.net/zhou_java_hui");
		ct.setSummary("博客列表2");
		ct.setCreatedDate(new Date());
		items.add(ct);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rssViewer");
		mav.addObject("feedContent", items);
		
		mav.setView(new RssViewer());
		return mav;

	}
	
}