package org.bear.bookstore.web.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.bear.bookstore.domain.Custom.Sex;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name = "country")  
@XmlType(propOrder = { "id", "cusName","phone","email","sex","address"}) 
public class CustomVo {
	private long id;
	@NotEmpty(message="客户名称不能为空")
	@XmlElement(name = "cus_name")  
	private String cusName;
	private String phone;
	private String email;
	private Sex sex;
	private String address;
}
