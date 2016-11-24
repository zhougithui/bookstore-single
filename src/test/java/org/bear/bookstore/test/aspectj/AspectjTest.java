package org.bear.bookstore.test.aspectj;

import org.bear.bookstore.test.aspectj.example1.CustomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * AOP的基本概念：
 * 1）aspect（切面）：实现了cross-cutting功能，是针对切面的模块。最常见的是logging模块，
 * 		这样，程序按功能被分为好几层，如果按传统的继承的话，商业模型继承日志模块的话根本没有什么意义，而通过创建一个logging切面就可以使用AOP来实现相同的功能了。
 * 2）jointpoint（连接点）：连接点是切面插入应用程序的地方，该点能被方法调用，而且也会被抛出意外。
 * 		连接点是应用程序提供给切面插入的地方，可以添加新的方法。
 * 3）advice（处理逻辑）：advice是我们切面功能的实现，它通知程序新的行为。如在logging里，logging
 * 		advice包括logging的实现代码，比如像写日志到一个文件中。advice在jointpoint处插入到应用程序中。
 * 4）pointcut（切点）：pointcut可以控制你把哪些advice应用于jointpoint上去，
 * 		通常你使用pointcuts通过正则表达式来把明显的名字和模式进行匹配应用。决定了那个jointpoint会获得通知。
 * 5）introduction：允许添加新的方法和属性到类中。
 * 6）target（目标类）：是指那些将使用advice的类，一般是指独立的那些商务模型。
 * 7)proxy（代理类）：使用了proxy的模式。是指应用了advice的对象，看起来和target对象很相似。
 * 8）weaving(插入）：是指应用aspects到一个target对象创建proxy对象的过程：complie time，classload time，runtime
 * 
 * @author q
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/test/resources/spring-aspectj.xml"}
		)
public class AspectjTest {
	@Autowired CustomService cusService;
	
	/**
	 * 如果是静态织入的加载时织入，则需要在vm运行参数指定加载的instrument
	 * -javaagent:F:/worksoftware/maven333/repos/org/springframework/spring-instrument/4.3.4.RELEASE/spring-instrument-4.3.4.RELEASE.jar
	 * Runtime Environment 				LoadTimeWeaver implementation 
	 * Running in Oracle’s WebLogic 	WebLogicLoadTimeWeaver 
	 * Running in Oracle’s GlassFish	GlassFishLoadTimeWeaver 
	 * Running in Apache Tomcat 		TomcatLoadTimeWeaver
	 * Running in Red Hat’s JBoss AS or WildFly JBossLoadTimeWeaver 
	 * Running in IBM’s WebSphere 		WebSphereLoadTimeWeaver 
	 * JVM started with Spring InstrumentationSavingAgent (java -javaagent:path/to/spring-instrument.jar) InstrumentationLoadTimeWeaver
	 * Fallback, expecting the underlying ClassLoader to follow common conventions 
	 * (e.g. applicable to TomcatInstrumentableClassLoader and Resin) ReflectiveLoadTimeWeaver
	 */
	@Test
	public void cusTest(){
		cusService.hello("zh");
	}
}
