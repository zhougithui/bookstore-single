package org.bear.bookstore.test.parentson.son;

import org.bear.bookstore.test.parentson.parent.B;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class A {
	private B b;
	public void hello(){
		b.hello();
	}
}
