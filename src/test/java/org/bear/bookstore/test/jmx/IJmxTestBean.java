package org.bear.bookstore.test.jmx;

public interface IJmxTestBean {
	public int add(int x, int y);

    public long myOperation();

    public int getAge();

    public void setAge(int age);

    public void setName(String name);

    public String getName();
}
