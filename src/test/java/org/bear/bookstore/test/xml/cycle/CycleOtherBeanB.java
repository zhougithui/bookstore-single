package org.bear.bookstore.test.xml.cycle;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

public class CycleOtherBeanB {
	@Autowired(required=true)
	private CycleOtherBeanA other1;
	
	@Setter
	private CycleOtherBeanA other2;
	
	public void hello(){
		System.out.println("hello othera1 null " + (this.other1 == null));
		System.out.println("hello othera2 null " + (this.other2 == null));
	}
	
	public CycleOtherBeanB(CycleOtherBeanA other){
		System.out.println("hello otherA ");
	}
}
