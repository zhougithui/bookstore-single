package org.bear.bookstore.test.xml;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.DelegatingEntityResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.xml.SimpleSaxErrorHandler;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * spring中xml文件验证（xsd、dtd）和读取
 * 
 * @author q
 *
 */
public class XmlSaxReaderTest {
	private static Log log = LogFactory.getLog(XmlSaxReaderTest.class);

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);

		try {
			factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", 
					"http://www.w3.org/2001/XMLSchema");
		}
		catch (IllegalArgumentException ex) {
			ParserConfigurationException pcex = new ParserConfigurationException(
					"Unable to validate using XSD: Your JAXP provider [" + factory +
					"] does not support XML Schema. Are you running on Java 1.4 with Apache Crimson? " +
					"Upgrade to Apache Xerces (or Java 1.5) for full XSD support.");
			pcex.initCause(ex);
		}
		
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		/**
		 * 设置实体解析器，也就是element解析器，默认情况下是从网络上获取xsd或者dtd文件，
		 * 这样会导致获取验证文件失败，导致xml解析失败的问题，spring通过重写EntityResolver
		 * 
		 * 1、如果是dtd声明，则是通过ClassPathResource(locations,class)，通过class.getResouce获取，也就是class类同级目录
		 * this.dtdResolver = new BeansDtdResolver(); dtd的解析器
		 * 
		 * 2、如果是xsd声明，则是通过schemaMappings存储位置信息，通过classloader类读取xsd文件
		 * 		key为systemId，value为地址信息
		 * 		Stores the mapping of schema URL -> local schema path
		 * 		private volatile Map<String, String> schemaMappings;
		 * this.schemaResolver = new PluggableSchemaResolver(classLoader); xsd的解析器
		 * 
		 * The location of the file that defines schema mappings.
		 * Can be present in multiple JAR files.
		 * public static final String DEFAULT_SCHEMA_MAPPINGS_LOCATION = "META-INF/spring.schemas";
		 */
		docBuilder.setEntityResolver(new DelegatingEntityResolver(XmlSaxReaderTest.class.getClassLoader()));
		docBuilder.setErrorHandler(new SimpleSaxErrorHandler(log));
		
		/**
		 * 指定编码的文件流，编码问题处理
		 */
		Resource rs = new ClassPathResource("spring-aop.xml");
		EncodedResource encodeResource = new EncodedResource(rs, Charset.defaultCharset());
		InputSource is = new InputSource(encodeResource.getInputStream());
		is.setEncoding(encodeResource.getEncoding());
		is.setCharacterStream(encodeResource.getReader());
		
		Document doc = docBuilder.parse(is);
		System.out.println(doc.getDocumentElement().getTagName());
		
	}
}
