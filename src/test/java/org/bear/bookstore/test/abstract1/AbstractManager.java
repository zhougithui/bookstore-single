package org.bear.bookstore.test.abstract1;

public abstract class AbstractManager {
	public void hello(){
		Manager manager = realHello();
		manager.hello();
	}
	protected abstract Manager realHello();
}
