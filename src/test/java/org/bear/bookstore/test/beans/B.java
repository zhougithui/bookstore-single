package org.bear.bookstore.test.beans;

public class B {
	private A a;
	
	public void hello(){
		a.hello();
		System.out.println("hello b ...");
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
	
	
}
