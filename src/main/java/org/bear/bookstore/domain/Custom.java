package org.bear.bookstore.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 客户
 * @author q
 *
 */
@Data
@Entity
public class Custom implements Serializable {
	private static final long serialVersionUID = -6126175292864588774L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Length(min=3, message="{custom.cusName.msg}")
	private String cusName;
	@NotNull
	@Length(min=11, max=11, message="{custom.phone.msg}")
	private String phone;
	private String email;
	@Enumerated(EnumType.ORDINAL)
	private Sex sex;
	private String address;
	//@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	public enum Sex{
		Male('男',1),
		Female('女',2);
		
		private char desc;
		private int value;
		Sex(char desc, int value){
			this.desc = desc;
			this.value = value;
		}
		public char getDesc() {
			return desc;
		}
		public int getValue() {
			return value;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(Sex.Female.value);
	}
}
