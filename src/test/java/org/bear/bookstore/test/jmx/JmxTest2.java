package org.bear.bookstore.test.jmx;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmxTest2 {
	public static void main(String[] args) throws IOException, InstanceNotFoundException, IntrospectionException, MalformedObjectNameException, ReflectionException, InterruptedException, AttributeNotFoundException, MBeanException {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("beans2.xml", JmxTest2.class);
	    
		MBeanServerConnection mb = app.getBean(MBeanServerConnection.class);
		/**
		 * 修改MBean的值，spring获取bean的值也会改变
		 */
		mb.invoke(new ObjectName("bean:name=testBean4"), "setName", new Object[]{"zh"}, new String[]{String.class.getName()});
		
		System.in.read();
	    ((ClassPathXmlApplicationContext)app).close();
	}
}
