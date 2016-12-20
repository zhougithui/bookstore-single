package org.bear.bookstore.test.beans;

import java.beans.PropertyEditorSupport;

public class AEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$" + text);
		setValue(new A());
	}
}
