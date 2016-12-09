package org.bear.bookstore.test.xml.simple;

import org.springframework.beans.factory.FactoryBean;

public class SpringBean6FactoryBean implements FactoryBean<MySpringBean6> {

	@Override
	public MySpringBean6 getObject() throws Exception {
		return MySpringBean6.instance();
	}

	@Override
	public Class<?> getObjectType() {
		return MySpringBean6.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
