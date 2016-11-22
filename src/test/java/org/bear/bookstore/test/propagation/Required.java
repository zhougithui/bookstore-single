package org.bear.bookstore.test.propagation;

import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.domain.Custom.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Required {
	@Autowired HibernateTemplate hibernateTemplate;
	
	@Transactional(
			propagation=Propagation.REQUIRED
			)
	public void a(){
		Custom cus = new Custom();
		cus.setAddress("北京市，朝阳区，十里河村");
		cus.setCusName("zhouhui");
		cus.setEmail("zh@qq.com");
		cus.setPhone("15555555555");
		cus.setSex(Sex.Male);
		hibernateTemplate.save(cus);
		
		b();
	}
	@Transactional(
			propagation=Propagation.REQUIRED
			)
	public void b(){
		int x = 1/0;
		System.out.println(x);
	}
	
}
