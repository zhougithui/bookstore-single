package org.bear.bookstore.dao;

import org.bear.bookstore.domain.Custom;

public interface ICustomDao {
	public void insert(Custom cus);

	public Custom select(int id);
}
