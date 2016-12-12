package org.bear.bookstore.test.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyErrorHandler implements ErrorHandler {

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		log.error(JSON.toJSONString(exception));
		throw exception;
	}

	@Override
	public void error(SAXParseException exception) throws SAXException {
		log.error(JSON.toJSONString(exception));
		throw exception;
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		log.error(JSON.toJSONString(exception));
		throw exception;
	}


}
