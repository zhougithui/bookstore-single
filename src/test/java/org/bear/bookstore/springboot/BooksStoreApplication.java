package org.bear.bookstore.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(value={"org.bear.bookstore.springboot"})
@ImportResource("classpath:org/bear/bookstore/springboot/application-context.xml")
public class BooksStoreApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(BooksStoreApplication.class, args);
	}
}
