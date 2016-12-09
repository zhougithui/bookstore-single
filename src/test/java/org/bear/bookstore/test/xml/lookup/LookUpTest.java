package org.bear.bookstore.test.xml.lookup;

public abstract class LookUpTest {
	public void showme(){
		this.getUser().showme();
	}
	public abstract User getUser();
}
