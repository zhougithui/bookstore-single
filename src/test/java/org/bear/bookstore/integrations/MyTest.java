package org.bear.bookstore.integrations;

import org.bear.bookstore.integrations.listener.MyCustomTestExecutionListener;
import org.bear.bookstore.test.aspectj.example1.CustomService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/*@ContextConfiguration
  @TestExecutionListeners({
    MyCustomTestExecutionListener.class,
    ServletTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    SqlScriptsTestExecutionListener.class
})*/
/**
 * 如果没有配置locations和classes则会在默认的地址找配置文件
 * ApplicationContext will be loaded from classpath:com/example/MyTest-context.xml
 */
@ContextConfiguration
@TestExecutionListeners(
	listeners = MyCustomTestExecutionListener.class,
	mergeMode = MergeMode.MERGE_WITH_DEFAULTS
)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MyTest {
	/**
	 * 如果没有配置locations和classes则会在默认的地址找配置文件，但存在static类
	 * ApplicationContext will be loaded from the static nested Config class
	 * @author q
	 *
	 */
	/*@Configuration
	static class Config {
		// this bean will be injected into the OrderServiceTest class
		@Bean
		public CustomService orderService() {
			CustomService customService = new CustomService();
			// set properties, etc.
			return customService;
		}
	}*/

	/*@Autowired
    private ApplicationContext applicationContext;*/
	@Autowired
    private WebApplicationContext wac;
}
