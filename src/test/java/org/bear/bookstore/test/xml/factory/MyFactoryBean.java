package org.bear.bookstore.test.xml.factory;

import org.springframework.beans.factory.FactoryBean;

import lombok.Setter;

public class MyFactoryBean implements FactoryBean<Car>{
	@Setter
	private String name;

	@Override
	public Car getObject() throws Exception {
		Car car = new Car();
		car.setName(name);
		return car;
	}

	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
