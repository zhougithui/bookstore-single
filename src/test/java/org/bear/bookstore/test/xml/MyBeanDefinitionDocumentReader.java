package org.bear.bookstore.test.xml;

import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.w3c.dom.Element;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 通过重载父类的方法，推xml解析进行前置和后置处理
 * @author q
 *
 */
@Slf4j
public class MyBeanDefinitionDocumentReader extends DefaultBeanDefinitionDocumentReader {
	
	@Override
	protected void preProcessXml(Element root) {
		//log.error(JSON.toJSONString(root));
	}
	
	@Override
	protected void postProcessXml(Element root) {
		//log.error(JSON.toJSONString(root));
	}
}
