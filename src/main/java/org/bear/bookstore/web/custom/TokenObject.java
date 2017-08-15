package org.bear.bookstore.web.custom;

import lombok.Data;

@Data
public class TokenObject {
	private Long expire = 7200L;
	private String access_token = "e3288f23-600c-4945-9afe-f27f6c97e036";
	private String success = "";
}
