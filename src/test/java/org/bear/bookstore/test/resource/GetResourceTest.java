package org.bear.bookstore.test.resource;

/**
 * java通过class和classLoader获取资源的方式
 * @author q
 *
 */
public class GetResourceTest {
	public static void main(String[] args) {
		//如果以'/'开头则从classpath路劲下获取
		System.out.println(GetResourceTest.class.getResource(""));
		System.out.println(GetResourceTest.class.getResource("/"));
		
		/**
		 * classloader不能以'/'开头，否则为空，classloader是从classpath跟路劲开始获取
		 */
		System.out.println(GetResourceTest.class.getClassLoader().getResource(""));
		System.out.println(GetResourceTest.class.getClassLoader().getResource("/"));
	}
}
