package org.bear.bookstore.web.vo;

import org.bear.bookstore.domain.Custom.Sex;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CustomVo {
	private long id;
	@NotEmpty(message="客户名称不能为空")
	private String cusName;
	private String phone;
	private String email;
	private Sex sex;
	private String address;
}
