package org.bear.bookstore.common.webinitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * servlet 3.0+的心特性
 * spring提供了初始化接口，并指定了@HandlesTypes，所以WebApplicationInitializer的子类都会被加载
 * 非抽象类都会被实例化，onStartup都会被调用
 * @HandlesTypes(WebApplicationInitializer.class)
 * public class SpringServletContainerInitializer implements ServletContainerInitializer
 * 
 * WebApplicationInitializer的实现类
 * AbstractAnnotationConfigDispatcherServletInitializer 抽象接口，提供注解式配置webapplication
 * AbstractDispatcherServletInitializer 抽象接口，提供codebase配置webapplication
 * @author q
 *
 */
public class BookStoreWebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("getMajorVersion=" + servletContext.getMajorVersion());
		/*XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");

        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");*/
	}

}
