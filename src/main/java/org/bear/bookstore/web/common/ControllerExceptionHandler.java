package org.bear.bookstore.web.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ControllerExceptionHandler {
	/**
	 * BindException
			400 (Bad Request)
		ConversionNotSupportedException
			500 (Internal Server Error)
		HttpMediaTypeNotAcceptableException
			406 (Not Acceptable)
		HttpMediaTypeNotSupportedException
			415 (Unsupported Media Type)
		HttpMessageNotReadableException
			400 (Bad Request)
		HttpMessageNotWritableException
			500 (Internal Server Error)
		HttpRequestMethodNotSupportedException
			405 (Method Not Allowed)
		MethodArgumentNotValidException
			400 (Bad Request)
		MissingServletRequestParameterException
			400 (Bad Request)
		MissingServletRequestPartException
			400 (Bad Request)
		NoHandlerFoundException
			404 (Not Found)
		NoSuchRequestHandlingMethodException
			404 (Not Found)
		TypeMismatchException
			400 (Bad Request)
		MissingPathVariableException
			500 (Internal Server Error)
		NoHandlerFoundException
			404 (Not Found)
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.MOVED_PERMANENTLY)
    public ModelAndView exceptionHandler(Exception ex){
		//如果配置了@ResponseStatus，则必须是301或者302，否则不会重定向，默认为302
		/**
		 * HttpStatus attributeStatusCode = (HttpStatus) request.getAttribute(View.RESPONSE_STATUS_ATTRIBUTE);
			if (this.statusCode != null) {
				response.setStatus(this.statusCode.value());
				response.setHeader("Location", encodedURL);
			}
			else if (attributeStatusCode != null) {
				response.setStatus(attributeStatusCode.value());
				response.setHeader("Location", encodedURL);
			}
			else {
				// Send status code 302 by default.
				response.sendRedirect(encodedURL);
			}
		 */
		//error跳转到ErrorController去
        //RedirectView redirectView = new RedirectView("error");
        //redirectView.setExpandUriTemplateVariables(false);
        //redirectView.setExposeModelAttributes(false);
        
		//未处理的错误会在web.xml中寻找error-page
        ModelAndView mv = new ModelAndView("redirect:/error");
        mv.addObject("exception", ex);
        //mv.setView(redirectView);
        
        
        ex.printStackTrace();
        return mv;
    }
	
	
	
	/**
	 * 当页面传输date类型参数的时候，通过此进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
