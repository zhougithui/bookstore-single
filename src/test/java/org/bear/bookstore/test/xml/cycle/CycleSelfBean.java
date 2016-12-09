package org.bear.bookstore.test.xml.cycle;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

public class CycleSelfBean {
	@Autowired(required=true)
	private CycleSelfBean self;
	
	@Setter
	private CycleSelfBean self1;
	
	public void hello(){
		System.out.println("hello self null " + (this.self == null));
		System.out.println("hello self1 == this " + (this.self1 == this));
	}
	
	/*public CycleSelfBean(CycleSelfBean self){
		System.out.println("hello constructor == this " + (this.self1 == this));
	}*/
}
