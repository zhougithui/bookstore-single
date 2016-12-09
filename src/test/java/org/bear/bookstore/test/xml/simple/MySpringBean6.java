package org.bear.bookstore.test.xml.simple;

public class MySpringBean6 {
	public static MySpringBean6 instance(){
		System.out.println("myspring6 instance...");
		return new MySpringBean6();
	}
}
