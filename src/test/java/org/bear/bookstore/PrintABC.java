package org.bear.bookstore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC {
    static Lock lock = new ReentrantLock();  //可重入锁
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();
    static Condition conditionC = lock.newCondition();

    public class PrintA implements Runnable {
        @Override
        public void run() {
        	try {
	        	lock.lock();
	        	for(int count = 0;count < 10;count++) {
	                System.out.print("A");
                    conditionB.signal();  //线程b唤醒,因为a打印完应该打印b
                    conditionA.await();  //线程a进入等待队列
	            }
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	} finally {
        		lock.unlock();
        	}
        }
    }

    public class PrintB implements Runnable {
        @Override
        public void run() {
        	try {
	        	lock.lock();
	        	for(int count = 0;count < 10;count++) {
	                System.out.print("B");
                    conditionC.signal();
                    conditionB.await();

	        	}
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	} finally {
        		lock.unlock();
        	}
        }
    }

    public class PrintC implements Runnable {
        @Override
        public void run() {
        	try {
        		lock.lock();
	            for(int count = 0;count < 10;count++) {
	                System.out.println("C" + count);
                    conditionA.signal();
                    conditionC.await();
	            }
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	} finally {
        		lock.unlock();
        	}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintABC printABCD = new PrintABC();
        new Thread(printABCD.new PrintA()).start();
        Thread.sleep(1);
        new Thread(printABCD.new PrintB()).start();
        Thread.sleep(1);
        new Thread(printABCD.new PrintC()).start();

    }

}