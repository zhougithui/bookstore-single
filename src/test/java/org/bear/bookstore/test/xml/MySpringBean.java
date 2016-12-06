package org.bear.bookstore.test.xml;

import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

public class MySpringBean {
	@Setter
	@Getter
	private String msg;
	@Setter
	@Getter
	private int i;
	@Setter
	@Getter
	private Properties pro;
	
	@Setter
	@Getter
	private MySpringBean2 myBean;
	
	public MySpringBean(){}
	
	public MySpringBean(String msg, int i) {
		super();
		this.msg = msg;
		this.i = i;
	}
	
	public MySpringBean(String msg, int i, MySpringBean2 myBean) {
		super();
		this.msg = msg;
		this.i = i;
		this.myBean = myBean;
	}

	public void hello() {
		System.out.println("hello world!");
	}
	
	public void hello2() {
		myBean.hello();
	}
	
	private void init(){
		System.out.println("init " + msg);
	}
	
	private void destroy(){
		System.out.println("destroy " + msg);
	}

}
