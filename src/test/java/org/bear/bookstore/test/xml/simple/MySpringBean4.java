package org.bear.bookstore.test.xml.simple;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Service
public class MySpringBean4 {
	@Value(value="${msg}")
	private String msg;
	@Value(value="${i}")
	private int i;

}
