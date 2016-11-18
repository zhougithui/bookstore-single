package org.bear.bookstore.test;

import org.bear.bookstore.domain.Custom;
import org.bear.bookstore.domain.Custom.Sex;

public class CustomTest {
	public static void main(String[] args) {
		Custom c = new Custom();
		c.setSex(Sex.Male);
		System.out.println(c.getSex().getDesc());
	}
}
