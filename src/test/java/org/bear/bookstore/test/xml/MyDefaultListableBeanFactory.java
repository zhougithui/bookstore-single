package org.bear.bookstore.test.xml;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;

public class MyDefaultListableBeanFactory extends DefaultListableBeanFactory implements EnvironmentCapable {
	private Environment environment;

	/**
	 * 可以实现自己的environment，系统默认为StandardEnvironment
	 */
	@Override
	public Environment getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(Environment environment){
		this.environment = environment;
	}
}
