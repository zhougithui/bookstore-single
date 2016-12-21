package org.bear.bookstore.common.webinitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

/**
 * servlet 3.0+的心特性
 * spring提供了初始化接口，并指定了@HandlesTypes，所以WebApplicationInitializer的子类都会被加载
 * 非抽象类都会被实例化，onStartup都会被调用
 * @HandlesTypes(WebApplicationInitializer.class)
 * public class SpringServletContainerInitializer implements ServletContainerInitializer
 * @author q
 *
 */
public class BookStoreWebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("getMajorVersion=" + servletContext.getMajorVersion());
	}

}
