package org.bear.bookstore.web.custom;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.service.ICustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.fastjson.JSON;

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
	
	@RequestMapping("/getCusById")
	@ResponseBody
	public void queryCustomPost(@RequestParam String param, HttpServletResponse res){
		HttpResult<List<Map<String,String>>> result = new HttpResult<>();
		result.setMsg("fds");
		result.setSuccess(true);
		List<Map<String,String>> data = new ArrayList<>();
		
		Map<String,String> map = new HashMap<>();
		map.put("name", "zmy");
		Map<String,String> map1 = new HashMap<>();
		map1.put("name", "zmy1");
		
		data.add(map);
		data.add(map1);
		
		result.setData(data);
		
		TokenObject token = new TokenObject();
		token.setSuccess(new Random().nextInt(100)%2 == 0 ? "" : "-1");
		try {
			res.getWriter().print(token.getSuccess().equals("-1") ? JSON.toJSONString(token) : JSON.toJSONString(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				res.getWriter().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
			@CookieValue(name="JSESSIONID", required=false) String cookie){
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
	
	
	//-------------------------freemarker + jsp + velocity------------------------------------
	/**
	 * 根据flag跳转至不同的模板
	 * @param flag 模板选择  ftl jsp vm 
	 * @param model
	 * @return
	 */
	@RequestMapping("/cusform/{flag}")
	public String cusform(@PathVariable String flag, Model model){
		if(!model.containsAttribute("cus")){
			model.addAttribute("cus", new Custom());
			model.addAttribute("flag", flag);
		}
		return "addUser" + StringUtils.capitalize(flag);
	}
	
	@RequestMapping("/saveCus/{flag}")
	public String cusform(@Valid Custom cus, BindingResult result,
			@PathVariable(required=false) String flag, Model model){
		if(result.hasErrors()){
			model.addAttribute("cus", cus);  
			model.addAttribute("org.springframework.validation.BindingResult.cus", result); 
			return cusform(flag, model);
		}
		model.addAttribute("cus", cus);
		return "showUser" + StringUtils.capitalize(flag);
	}
	
	@RequestMapping("/token")
	@ResponseBody
	public TokenObject token(){
		TokenObject token = new TokenObject();
		return token;
	}
}
