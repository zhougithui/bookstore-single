package org.bear.bookstore.service;

import org.bear.bookstore.domain.Custom;

public interface ICustomService {
	public void insert(Custom cus);

	public Custom select(int id);
}
