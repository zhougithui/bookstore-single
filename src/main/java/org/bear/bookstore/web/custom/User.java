package org.bear.bookstore.web.custom;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
	private String email;
	private int loginFailed;
	public User(String email, int loginFailed) {
		this.email = email;
		this.loginFailed = loginFailed;
	}
}
