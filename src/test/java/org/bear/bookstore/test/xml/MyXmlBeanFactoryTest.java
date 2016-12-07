package org.bear.bookstore.test.xml;

import org.bear.bookstore.test.xml.cycle.CycleOtherBeanA;
import org.bear.bookstore.test.xml.cycle.CycleOtherBeanB;
import org.bear.bookstore.test.xml.cycle.CycleSelfBean;
import org.bear.bookstore.test.xml.factory.Car;
import org.bear.bookstore.test.xml.factory.MyFactoryBean;
import org.bear.bookstore.test.xml.lookup.LookUpTest;
import org.bear.bookstore.test.xml.replace.MyValueCalculator;
import org.bear.bookstore.test.xml.simple.MySpringBean;
import org.bear.bookstore.test.xml.simple.MySpringBean3;
import org.bear.bookstore.test.xml.simple.MySpringBean4;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSON;

public class MyXmlBeanFactoryTest {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		/*
		 * spring中多出用此进行jar包或者版本的标识，判断是否有些功能启用，好进行日志提示
		 * private static Class<?> javaUtilOptionalClass = null;
			private static Class<?> javaxInjectProviderClass = null;
			static {
				try {
					javaUtilOptionalClass =
							ClassUtils.forName("java.util.Optional", DefaultListableBeanFactory.class.getClassLoader());
				}
				catch (ClassNotFoundException ex) {
					// Java 8 not available - Optional references simply not supported then.
				}
				try {
					javaxInjectProviderClass =
							ClassUtils.forName("javax.inject.Provider", DefaultListableBeanFactory.class.getClassLoader());
				}
				catch (ClassNotFoundException ex) {
					// JSR-330 API not available - Provider interface simply not supported then.
				}
			}
		 */
		MyDefaultListableBeanFactory beanFactory = new MyDefaultListableBeanFactory();
		/**
		 * 环境变量拓展
		 */
		beanFactory.setEnvironment(new StandardEnvironment());
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.setProblemReporter(new MyProblenReporter());
		/**
		 * 读取事件监听器 defauts配置监听、component定义监听、import定义监听、alias定义监听
		 */
		reader.setEventListener(new MyReaderEventListner());
		reader.setSourceExtractor(new MySourceExtractor());
		//实现自己的xml读取器
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
			System.out.println(e.getMessage());
		}
		
		System.out.println("^^^^^^^^^^^^^^springBean16^^^^^^^^^^^^^^^^^^^");
		MySpringBean bean16 = beanFactory.getBean("springBean16",MySpringBean.class);
		System.out.println(bean16.getMsg());
		
		System.out.println("^^^^^^^^^^^^^^springBean17^^^^^^^^^^^^^^^^^^^");
		MySpringBean bean17 = beanFactory.getBean("springBean17",MySpringBean.class);
		System.out.println(bean17.getMsg());
		
		System.out.println("^^^^^^^^^^^^^^springBean3^^^^^^^^^^^^^^^^^^^");
		MySpringBean3 bean31 = beanFactory.getBean("springBean31",MySpringBean3.class);
		bean31.hello();
		MySpringBean3 bean32 = beanFactory.getBean("springBean32",MySpringBean3.class);
		bean32.hello();
		System.out.println("^^^^^^^^^^^^^^springBean31/32^^^^^^^^^^^^^^^^^^^");
		/**
		 * 注册bean的处理器，在bean初始化前后进行bean中AutowiredAnnotation的处理
		 */
		beanFactory.addBeanPostProcessor(beanFactory.getBean(AutowiredAnnotationBeanPostProcessor.class));
		MySpringBean3 bean3 = beanFactory.getBean("springBean3",MySpringBean3.class);
		bean3.hello();
		
		System.out.println("^^^^^^^^^^^^^^myValueCalculator^^^^^^^^^^^^^^^^^^^");
		/**
		 * 判断如果存在method覆盖的类，则采用cglib
		 * AbstractAutowireCapableBeanFactory.instantiateBean(使用的CglibSubclassingInstantiationStrategy)
		 * 调用CglibSubclassingInstantiationStrategy的instantiate(mbd, beanName, parent)方法
		 * if (bd.getMethodOverrides().isEmpty()) {
		 * 	通过反射创建类的实例
		 * 	return BeanUtils.instantiateClass(constructorToUse);
		 * }else{
		 * 	通过cglib创建类的实例
		 * 	return instantiateWithMethodInjection(bd, beanName, owner);
		 * }
		 * 也就是下面这行
		 * // Must generate CGLIB subclass...
		 * return new CglibSubclassCreator(bd, owner).instantiate(ctor, args);
		 */
		//可以看到LookupOverride和ReplaceOverride都是如此实现的
		// SPR-10785: set callbacks directly on the instance instead of in the
		// enhanced class (via the Enhancer) in order to avoid memory leaks.
		/*Factory factory = (Factory) instance;
		factory.setCallbacks(new Callback[] {NoOp.INSTANCE,
				new LookupOverrideMethodInterceptor(this.beanDefinition, this.owner),
				new ReplaceOverrideMethodInterceptor(this.beanDefinition, this.owner)});
		return instance;*/
		
		MyValueCalculator myValueCalculator = beanFactory.getBean("myValueCalculator",MyValueCalculator.class);
		System.out.println(myValueCalculator.computeValue("a", "b"));
		System.out.println(myValueCalculator.computeValue3("a", "b"));
		System.out.println(myValueCalculator.computeValue1("a", "b"));
		
		
		System.out.println("^^^^^^^^^^^^^^springbean4^^^^^^^^^^^^^^^^^^^");
		/**
		 * 加载配置的配置文件，读取文件中的内容
		 * context包中beanFactory包装类就是这么调用的，等分析后详解
		 */
		PropertySourcesPlaceholderConfigurer beanFactoryPost = beanFactory.getBean(PropertySourcesPlaceholderConfigurer.class);
		beanFactoryPost.postProcessBeanFactory(beanFactory);
		MySpringBean4 bean4 = beanFactory.getBean(MySpringBean4.class);
		System.out.println(JSON.toJSON(bean4));
		
		System.out.println("^^^^^^^^^^^^^^lookup^^^^^^^^^^^^^^^^^^^");
		LookUpTest lookup = beanFactory.getBean(LookUpTest.class);
		lookup.showme();
		
		System.out.println("^^^^^^^^^^^^^^factorybean^^^^^^^^^^^^^^^^^^^");
		Car car = beanFactory.getBean(Car.class);
		System.out.println(car.getName());
		/**
		 * 获取factoryBean本身
		 */
		MyFactoryBean carFactory = beanFactory.getBean("&car",MyFactoryBean.class);
		System.out.println(carFactory.getObjectType().getName());
		
		
		System.out.println("^^^^^^^^^^^^^^cycle self^^^^^^^^^^^^^^^^^^^");
		try {
			CycleSelfBean self = beanFactory.getBean(CycleSelfBean.class);
			self.hello();
		} catch (BeansException e) {
			e.printStackTrace();
		}
		System.out.println("^^^^^^^^^^^^^^cycle other^^^^^^^^^^^^^^^^^^^");
		try {
			CycleOtherBeanA othera = beanFactory.getBean(CycleOtherBeanA.class);
			CycleOtherBeanB otherb = beanFactory.getBean(CycleOtherBeanB.class);
			othera.hello();
			otherb.hello();
		} catch (BeansException e) {
			e.printStackTrace();
		}
		
		/**
		 * 销毁定义了销毁方法的并且为单例的bean
		 * 会将已经初始化的实例进行销毁，调用实例的destroy方法，没有则内部打印日志信息
		 */
		beanFactory.destroySingletons();
	}

}
