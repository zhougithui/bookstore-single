package org.bear.bookstore.lamda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LamdaTest {
	public static void main(String[] args) {
		List<Test1> list = new ArrayList<>();
		Test1 t = new Test1() {
		};
	}
}

interface Test1{
	default String f(){
		return "";
	}
}
