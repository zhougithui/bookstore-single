package org.bear.bookstore.test.jmx;

public class JmxTestBean implements IJmxTestBean{

    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int add(int x, int y) {
        return x + y;
    }

    public void dontExposeMe() {
        throw new RuntimeException();
    }

	@Override
	public long myOperation() {
		return 1L;
	}
}