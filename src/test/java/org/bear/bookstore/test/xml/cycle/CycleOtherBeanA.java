package org.bear.bookstore.test.xml.cycle;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

public class CycleOtherBeanA {
	@Autowired(required=true)
	private CycleOtherBeanB other1;
	
	@Setter
	private CycleOtherBeanB other2;
	
	public void hello(){
		System.out.println("hello otherb1 null " + (this.other1 == null));
		System.out.println("hello otherb2 null " + (this.other2 == null));
	}
	
	/*public CycleOtherBeanA(CycleOtherBeanB other){
		System.out.println("hello otherB ");
	}*/
}
