package org.bear.bookstore.web.jms;

import org.bear.bookstore.domain.Custom;

public interface ICustomService {
	public void insert(Custom cus);

	public Custom select(int id);
}
