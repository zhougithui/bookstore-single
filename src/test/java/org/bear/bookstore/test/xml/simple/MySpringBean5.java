package org.bear.bookstore.test.xml.simple;

import org.springframework.stereotype.Service;

@Service
public class MySpringBean5 implements IMySpringBean{

	@Override
	public void hello() {
		System.out.println("hello imyspringbean");
	}

}
