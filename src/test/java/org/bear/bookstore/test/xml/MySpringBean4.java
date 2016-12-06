package org.bear.bookstore.test.xml;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MySpringBean4 {
	@Value(value="${msg}")
	private String msg;
	@Value(value="${i}")
	private int i;

}
