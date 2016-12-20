package org.bear.bookstore.test.oxm;

import javax.xml.transform.stream.StreamResult;

import org.bear.bookstore.test.oxm.example4.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * JAXBContext类，是应用的入口，用于管理XML/Java绑定信息。
	Marshaller接口，将Java对象序列化为XML数据。
	Unmarshaller接口，将XML数据反序列化为Java对象。
	 
	@XmlType，将Java类或枚举类型映射到XML模式类型
	@XmlAccessorType(XmlAccessType.FIELD) ，控制字段或属性的序列化。FIELD表示JAXB将自动绑定Java类中的每个非静态的（static）、非瞬态的（由@XmlTransient标 注）字段到XML。其他值还有XmlAccessType.PROPERTY和XmlAccessType.NONE。
	@XmlAccessorOrder，控制JAXB 绑定类中属性和字段的排序。
	@XmlJavaTypeAdapter，使用定制的适配器（即扩展抽象类XmlAdapter并覆盖marshal()和unmarshal()方法），以序列化Java类为XML。
	@XmlElementWrapper ，对于数组或集合（即包含多个元素的成员变量），生成一个包装该数组或集合的XML元素（称为包装器）。
	@XmlRootElement，将Java类或枚举类型映射到XML元素。
	@XmlElement，将Java类的一个属性映射到与属性同名的一个XML元素。
	@XmlAttribute，将Java类的一个属性映射到与属性同名的一个XML属性。
 * @author q
 *
 */
public class OxmTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("spring-app.xml",OxmTest.class);
		beanFactory.registerShutdownHook();
		
		Jaxb2Marshaller marshaller = beanFactory.getBean("marshaller", Jaxb2Marshaller.class);
		
		/*Country country = new Country();  
        country.setName("中国");  
  
        List<Province> list = new ArrayList<Province>();  
        Province province = new Province();  
        province.setName("江苏省");  
        province.setProvCity("南京市");  
        Province province2 = new Province();  
        province2.setName("浙江省");  
        province2.setProvCity("杭州市");  
        list.add(province);  
        list.add(province2);  
  
        country.setProvinceList(list); 
		marshaller.marshal(country, new StreamResult(System.out));*/
		
		Student s = new Student();
		s.setId(1);
		s.setName("zh");
		
		marshaller.marshal(s, new StreamResult(System.out));
		
		beanFactory.close();
	}
}
