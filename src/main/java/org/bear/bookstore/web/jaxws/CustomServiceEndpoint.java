package org.bear.bookstore.web.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.service.ICustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebService(serviceName="jaxCustomService", endpointInterface="org.bear.bookstore.web.jaxws.ICustomService")
public class CustomServiceEndpoint /*extends SpringBeanAutowiringSupport*/ implements org.bear.bookstore.web.jaxws.ICustomService{

    @Autowired
    private ICustomService biz;

    @WebMethod
    public Custom select(int id) {
        return biz.select(id);
    }

	@Override
	public void insert(Custom cus) {
		biz.insert(cus);
	}

}