package org.bear.bookstore.test.jmx;

import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

public class JmxTest2 {
	public static void main(String[] args) throws IOException, InstanceNotFoundException, IntrospectionException, MalformedObjectNameException, ReflectionException {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("beans2.xml", JmxTest2.class);
	    
		MBeanServerConnection mb = app.getBean(MBeanServerConnection.class);
		MBeanInfo info = mb.getMBeanInfo(new ObjectName("bean:name=testBean4"));
		System.out.println(JSON.toJSON(info));
		
		System.in.read();
	    ((ClassPathXmlApplicationContext)app).close();
	}
}
