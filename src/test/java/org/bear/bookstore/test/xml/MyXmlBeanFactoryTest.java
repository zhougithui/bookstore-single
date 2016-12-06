package org.bear.bookstore.test.xml;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class MyXmlBeanFactoryTest {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.setProblemReporter(new MyProblenReporter());
		/**
		 * 读取事件监听器 defauts配置监听、component定义监听、import定义监听、alias定义监听
		 */
		reader.setEventListener(new MyReaderEventListner());
		reader.setSourceExtractor(new MySourceExtractor());
		//实现在即的xml读取器
		//reader.setDocumentLoader(documentLoader);
		/**
		 * xml解析异常处理器
		 */
		reader.setErrorHandler(new MyErrorHandler());
		/**
		 * bean读取器class定义，
		 * @see XmlBeanDefinitionReader.#createBeanDefinitionDocumentReader 
		 * 		直接通过@see BeanUtils.instantiateClass(this.documentReaderClass)初始化reader
		 */
		reader.setDocumentReaderClass(MyBeanDefinitionDocumentReader.class);
		
		int beanDefinitionCount = reader.loadBeanDefinitions(new ClassPathResource("spring-app.xml", MyXmlBeanFactoryTest.class));
		System.out.println("beanDefinition count=" + beanDefinitionCount);
		
		System.out.println("^^^^^^^^^^^^^^springBean10^^^^^^^^^^^^^^^^^^^");
		MySpringBean bean = beanFactory.getBean(MySpringBean.class);
		System.out.println(bean.getMsg());
		
		System.out.println("^^^^^^^^^^^^^^springBean12^^^^^^^^^^^^^^^^^^^");
		MySpringBean bean12 = beanFactory.getBean("springBean12",MySpringBean.class);
		System.out.println(bean12.getPro().get("a"));
		System.out.println(bean12.getPro().get("aa"));
		System.out.println(bean12.getMsg());
		
		System.out.println("^^^^^^^^^^^^^^springBean13^^^^^^^^^^^^^^^^^^^");
		MySpringBean bean13 = beanFactory.getBean("springBean13",MySpringBean.class);
		System.out.println(bean13.getMsg());
		
		System.out.println("^^^^^^^^^^^^^^springBean14^^^^^^^^^^^^^^^^^^^");
		MySpringBean bean14 = beanFactory.getBean("springBean14",MySpringBean.class);
		System.out.println(bean14.getMsg());
		
		System.out.println("^^^^^^^^^^^^^^springBean15^^^^^^^^^^^^^^^^^^^");
		try {
			MySpringBean bean15 = beanFactory.getBean("springBean15",MySpringBean.class);
			System.out.println(bean15.getMsg());
		} catch (BeansException e) {
			e.printStackTrace();
		}
		
		System.out.println("^^^^^^^^^^^^^^springBean16^^^^^^^^^^^^^^^^^^^");
		MySpringBean bean16 = beanFactory.getBean("springBean16",MySpringBean.class);
		System.out.println(bean16.getMsg());
		
		System.out.println("^^^^^^^^^^^^^^springBean17^^^^^^^^^^^^^^^^^^^");
		MySpringBean bean17 = beanFactory.getBean("springBean17",MySpringBean.class);
		System.out.println(bean17.getMsg());
		
		System.out.println("^^^^^^^^^^^^^^myValueCalculator^^^^^^^^^^^^^^^^^^^");
		MyValueCalculator myValueCalculator = beanFactory.getBean("myValueCalculator",MyValueCalculator.class);
		System.out.println(myValueCalculator.computeValue("a", "b"));
		System.out.println(myValueCalculator.computeValue3("a", "b"));
		System.out.println(myValueCalculator.computeValue1("a", "b"));
		
		/**
		 * 销毁定义了销毁方法的并且为单例的bean
		 */
		beanFactory.destroySingletons();
	}

}
