package org.bear.bookstore.domain;

import lombok.Data;

/**
 * 客户
 * @author q
 *
 */
@Data
public class Custom {
	private long id;
	private String cusName;
	private String phone;
	private String email;
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
