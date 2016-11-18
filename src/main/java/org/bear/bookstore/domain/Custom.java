package org.bear.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 客户
 * @author q
 *
 */
@Data
@Entity
public class Custom {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String cusName;
	private String phone;
	private String email;
	@Enumerated(EnumType.ORDINAL)
	private Sex sex;
	private String address;
	
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
