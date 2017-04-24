package org.bear.bookstore.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import com.alibaba.fastjson.JSON;

public class AtomicTest {
	public static void main(String[] args) {
		/*AtomicInteger ai = new AtomicInteger(0);
		for(int i=0; i<200; i++){
			new Thread(() -> {
				int x = ai.get();
				*//**
				 * 并发修改的情况下回出现失败
				 *//*
				ai.compareAndSet(x, x+1);
			}).start();;
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ai.get());*/
		
		/*AtomicIntegerArray ai = new AtomicIntegerArray(10);
		
		for(int i=0; i<300; i++){
			int y = i%10;
			int update = i;
			new Thread(() -> {
				int expect = ai.get(0);
				*//**
				 * 并发修改的情况下回出现失败
				 *//*
				boolean b = ai.compareAndSet(0, expect, update);
				if(!b){
					System.out.println(b);
				}
			}).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(ai));*/
		
		/*AtomicIntegerFieldUpdater<AtomicTest> ai = AtomicIntegerFieldUpdater.newUpdater(AtomicTest.class, "name");
		AtomicTest a = new AtomicTest();
		for(int i=0; i<300; i++){
			int x = i;
			new Thread(() -> {
				boolean b = ai.compareAndSet(a, a.name, x);
				if(!b){
					System.out.println(b);
				}
			}).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(a));*/
		
		System.out.println(Integer.toBinaryString(1 << 16));
		System.out.println(Integer.toBinaryString((1 << 16) - 1));
		System.out.println(Integer.toBinaryString(3 & ((1 << 16) - 1)));
		
	}
	public volatile int name;
}
