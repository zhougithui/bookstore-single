package org.bear.bookstore.thread;

/**
 * (01) wait()是让线程由“运行状态”进入到“等待(阻塞)状态”，而不yield()是让线程由“运行状态”进入到“就绪状态”。
 * (02) wait()是会线程释放它所持有对象的同步锁，而yield()方法不会释放锁。
 * @author q
 *
 */
class ThreadA extends Thread{
    public ThreadA(String name){ 
        super(name); 
    } 
    public synchronized void run(){ 
        for(int i=0; i <10; i++){ 
            System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i); 
            // i整除4时，调用yield
            if (i%4 == 0)
                Thread.yield();
        } 
    } 
} 

public class YieldTest{ 
    public static void main(String[] args){ 
        ThreadA t1 = new ThreadA("t1"); 
        ThreadA t2 = new ThreadA("t2"); 
        t1.start(); 
        t2.start();
    } 
}