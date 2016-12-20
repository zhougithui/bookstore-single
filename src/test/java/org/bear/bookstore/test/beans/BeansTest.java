package org.bear.bookstore.test.beans;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.InvocationTargetException;

public class BeansTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		B b = new B();
		
		BeanInfo beanInfo = new ABeanInfo();
		PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
		if (proDescrtptors != null && proDescrtptors.length > 0) {
			for (PropertyDescriptor propDesc : proDescrtptors) {
				if (propDesc.getName().equals("a")) {
					PropertyEditor e = propDesc.createPropertyEditor(b);
					e.setAsText("fdasfdasfdas");
					System.out.println(e.getValue().getClass().getName());
					break;
				}
			}
		}
		b.hello();
	}
}
