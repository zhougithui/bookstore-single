package org.bear.bookstore.test.abstract1;

public class Manager extends AbstractManager {
	private int i = 1;
	@Override
	protected Manager realHello() {
		return new Manager();
	}
	public void hello() {
		System.out.println("hello " + i++);
	}
}
