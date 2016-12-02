package org.bear.bookstore.test.xml;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class MyXmlBeanFactoryTest {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		
		int beanDefinitionCount = reader.loadBeanDefinitions(new ClassPathResource("spring-app.xml", MyXmlBeanFactoryTest.class));
		System.out.println("beanDefinition count=" + beanDefinitionCount);
		
		MySpringBean bean = beanFactory.getBean(MySpringBean.class);
		bean.hello();
	}

}
