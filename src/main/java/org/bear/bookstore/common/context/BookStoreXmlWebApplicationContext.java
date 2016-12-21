package org.bear.bookstore.common.context;

import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 拓展XmlWebApplicationContext
 * 
 * @author q
 *
 */
public class BookStoreXmlWebApplicationContext extends XmlWebApplicationContext {
	
	public BookStoreXmlWebApplicationContext(){
		System.out.println("init BookStoreXmlWebApplicationContext..");
	}
}
