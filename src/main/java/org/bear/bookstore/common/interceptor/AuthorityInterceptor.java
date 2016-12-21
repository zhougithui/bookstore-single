package org.bear.bookstore.common.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * 权限拦截器
 * @author q
 *
 */
public class AuthorityInterceptor implements WebRequestInterceptor {

	@Override
	public void preHandle(WebRequest request) throws Exception {
		
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		
	}

}
