package org.bear.bookstore.common.servlet;

import javax.servlet.ServletException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class DispatcherServletExt extends DispatcherServlet {
	private static final long serialVersionUID = 2061104342154771764L;

	public DispatcherServletExt(){
		super();
		//进行属性检查，必须要设置的属性
		addRequiredProperty("contextConfigLocation");
	}
	
	@Override
	protected void initFrameworkServlet() throws ServletException {
		//一定要加上这句，这样就算父类改了代码，也不会把父类的逻辑覆盖
		super.initFrameworkServlet();
		//编写自己的初始化逻辑
		
	}
	
	/**
	 * 这个时候applicationContext还没有refresh
	 */
	@Override
	protected void postProcessWebApplicationContext(ConfigurableWebApplicationContext wac) {
		super.postProcessWebApplicationContext(wac);
		//编写自己的后续处理逻辑
		
	}
	
	/**
	 * 在postProcessWebApplicationContext返回后执行
	 * registering property sources or activating profiles against the context's environment
	 */
	@Override
	protected void applyInitializers(ConfigurableApplicationContext wac) {
		super.applyInitializers(wac);
		//编写自己的后续处理逻辑
		
	}
	
}
