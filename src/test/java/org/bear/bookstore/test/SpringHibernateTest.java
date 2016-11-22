package org.bear.bookstore.test;

import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.domain.Custom.Sex;
import org.bear.bookstore.service.ICustomService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class SpringHibernateTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-app.xml","classpath:spring-hibernate.xml");
		ac.registerShutdownHook();
		HibernateTemplate hibernateTemplate = ac.getBean(HibernateTemplate.class);
		
		ICustomService cusService = ac.getBean(ICustomService.class);
		/*Custom cus = new Custom();
		cus.setAddress("北京市，朝阳区，十里河村");
		cus.setCusName("zhouhui");
		cus.setEmail("zh@qq.com");
		cus.setPhone("15555555555");
		cus.setSex(Sex.Male);
		
		cusService.insert(cus);*/
		/**
		 * 如果不激活事务同步，怎会出现如下异常
		 * Could not obtain transaction-synchronized Session for current thread
		 */
		TransactionSynchronizationManager.initSynchronization();
		System.out.println(cusService.select(2));
		ac.close();
	}
}
