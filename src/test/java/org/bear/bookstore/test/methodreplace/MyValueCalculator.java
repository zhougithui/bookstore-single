package org.bear.bookstore.test.methodreplace;

public class MyValueCalculator {

    public String computeValue(String input, String str1) {
    	// some real code...
    	System.out.println(111);
		return input;
    }
    
    public static String computeValue1(String input, String str1) {
    	// some real code...
    	System.out.println(222);
		return input;
    }

    public final String computeValue3(String input, String str1) {
    	// some real code...
    	System.out.println(333);
		return input;
    }

}