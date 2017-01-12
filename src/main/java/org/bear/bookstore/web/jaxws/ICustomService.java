package org.bear.bookstore.web.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.bear.bookstore.domain.Custom;

@WebService
public interface ICustomService {
	public void insert(Custom cus);

	@WebMethod
	public Custom select(int id);
}
