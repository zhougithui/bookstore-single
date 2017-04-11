package org.bear.bookstore.map;

import java.util.Set;
import java.util.TreeMap;

public class MapTest {
	public static void main(String[] args) {
		TreeMap<String, String> map = new TreeMap<>();
		map.put("name", "zh");
		map.put("age", "12131");
		map.put("anther", "zmy");
		
		Set<String> s = map.keySet();
		for(String value : map.values()){
			System.out.println(value);
		}
	}
}
