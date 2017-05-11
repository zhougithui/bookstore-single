package org.bear.bookstore.concurrent;

import java.util.PriorityQueue;

public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<String> queue = new PriorityQueue<>((a,b)->{
			return a.compareTo(b);
		});
		
		queue.add("a");
		queue.add("x");
		queue.add("c");
		queue.add("e");
		queue.add("a");
		
		
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		
		int x = 15 & (("abcd".hashCode()) ^ ("abcd".hashCode() >>> 16));
		System.out.println(Integer.toBinaryString("abcd".hashCode()));
		System.out.println(Integer.toBinaryString("abcd".hashCode() >>> 16));
		System.out.println(Integer.toBinaryString(("abcd".hashCode()) ^ ("abcd".hashCode() >>> 16)));
		System.out.println(Integer.toBinaryString(15));
		System.out.println(x);
	}
}
