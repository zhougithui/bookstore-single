package org.bear.bookstore.web.custom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.service.ICustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/api/cus")
public class CustomController {
	@Autowired
	private ICustomService customService;
	
	@RequestMapping("/getCusById/{id}")
	@ResponseBody
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
	@ResponseBody
	public Custom queryCustomByName(@Valid Custom cus, BindingResult result, 
			@CookieValue("JSESSIONID") String cookie){
		return cus;
	}
	
	@RequestMapping("/toAdd")
	public String toAddUser(HttpServletRequest request){
		Locale locale = RequestContextUtils.getLocale(request);
		TimeZone timeZone = RequestContextUtils.getTimeZone(request);
		return "addUser";
	}
	
	/**
	 * <html>
		    <head>
		        <title>Upload a file please</title>
		    </head>
		    <body>
		        <h1>Please upload a file</h1>
		        <form method="post" action="/form" enctype="multipart/form-data">
		            <input type="text" name="name"/>
		            <input type="file" name="file"/>
		            <input type="submit"/>
		        </form>
		    </body>
		</html>
	 */
	/**
	 * 如果是使用CommonsMultipartResolver，则为MultipartFile
	 * @param name
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file)
			throws IOException {

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			// store the bytes somewhere
			return "redirect:uploadSuccess";
		}

		return "redirect:uploadFailure";
	}
	
	/**
	 * 如果是使用MultipartHttpServletRequest ，则为Part
	 * 为servlet3新特性
	 * @param name
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/form1", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") Part file)
			throws IOException {
		InputStream inputStream = file.getInputStream();
		// store bytes from uploaded file somewhere
		return "redirect:uploadSuccess";
	}
}
