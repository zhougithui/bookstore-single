package org.bear.bookstore.web.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonpAdvice() {
    	//当请求参数包含callback或者jsonp时返回jsonp请求参数
        super("callback", "jsonp");
    }
}