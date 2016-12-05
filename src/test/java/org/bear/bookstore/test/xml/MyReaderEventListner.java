package org.bear.bookstore.test.xml;

import org.springframework.beans.factory.parsing.AliasDefinition;
import org.springframework.beans.factory.parsing.ComponentDefinition;
import org.springframework.beans.factory.parsing.DefaultsDefinition;
import org.springframework.beans.factory.parsing.ImportDefinition;
import org.springframework.beans.factory.parsing.ReaderEventListener;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyReaderEventListner implements ReaderEventListener {

	@Override
	public void defaultsRegistered(DefaultsDefinition defaultsDefinition) {
		log.error(JSON.toJSONString(defaultsDefinition));
	}

	@Override
	public void componentRegistered(ComponentDefinition componentDefinition) {
		/**
		 * java.lang.IllegalStateException: Bean class name [org.bear.bookstore.test.xml.MySpringBean] has not been resolved into an actual Class
		 */
		//log.error(JSON.toJSONString(componentDefinition));
	}

	@Override
	public void aliasRegistered(AliasDefinition aliasDefinition) {
		log.error(JSON.toJSONString(aliasDefinition));
	}

	@Override
	public void importProcessed(ImportDefinition importDefinition) {
		log.error(JSON.toJSONString(importDefinition));
	}

}
