package org.bear.bookstore.test.resource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

/**
 * spring 资源获取策略
 * @author q
 *
 */
public class ResourceTest {
	public static void main(String[] args) throws IOException, URISyntaxException {
		/**
		 * UriResource，通过网络协议读取文件资源
		 */
		Resource res = new UrlResource("file:///D:/API/JAVA_API_1.7.chm");
		res.getFile();
		
		URI uri = new URI("file:///D:/API/JAVA_API_1.7.chm");
		System.out.println(uri.getSchemeSpecificPart());
		
		File file = new File("///D:/API/JAVA_API_1.7.chm");
		System.out.println(file.exists());
		
		/**
		 * ClassPathResource最后通过this.classLoader.getResource(this.path)来获取资源，所以必须制定包路劲
		 * @see GetResourceTest
		 */
		res = new ClassPathResource("org/bear/bookstore/test/resource/application-orcl1.properties");
		System.out.println(res.getFile().exists());
		
		/**
		 * InputStream不做解读
		 */
		//res = new InputStreamResource(null);
		
		/**
		 * To be used as placeholder if a {@code Resource} argument is
		 * expected by an API but not necessarily used for actual reading
		 */
		//res = new DescriptiveResource("file from native....");
		
		/**
		 * ByteArrayResource不具体解读
		 */
		//res = new ByteArrayResource(null);
		
		/**
		 * FileSystemResource直接采用Java的File作为流
		 */
		res = new FileSystemResource("D:/API/JAVA_API_1.7.chm");
		System.out.println("FileSystemResource:" + res.getFile().exists());
		
		/**
		 * PathResource直接采用java中@see java.nio.Paths 获取流
		 */
		res = new PathResource("D:/API/JAVA_API_1.7.chm");
		System.out.println("PathResource:" + res.getFile().exists());
	}
}
