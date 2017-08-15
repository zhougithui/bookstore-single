package org.bear.bookstore.web.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@RequestMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file){
		System.out.println(file.getOriginalFilename());
		return "ok";
	}
}
