package org.bear.bookstore.concurrent;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockQueueTest {
	public static void main(String[] args) {
		ArrayBlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(10, true, new ArrayList<>());
	}
}
