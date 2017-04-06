package org.bear.bookstore;
public class MultiThread {
     
    public static void main (String[] args) throws Exception {
        Object A = new Object ();
        Object B = new Object ();
        Object C = new Object ();
         
        Thread t1 = new Thread (new Print(A, B), "A");
         
        Thread t2 = new Thread (new Print(B, C), "B");
         
        Thread t3 = new Thread (new Print(C, A), "C");
         
        // 为了让线程依次启动 所以在Main函数所在的线程 添加了一个sleep(1)
        t1.start();
        Thread.sleep(1);    
        t2.start();
        Thread.sleep(1);
        t3.start();
         
    }
}
 
 
 
 
class Print implements Runnable {
     
    private Object self;
    private Object next;
     
    public Print (Object self, Object next) throws InterruptedException {
        this.self = self;
        this.next = next;
 
    }
     
    public void run () {
         
        for (int i = 0; i < 10; i++) {
            synchronized (self) {
                synchronized (next) {
                    System.out.print(Thread.currentThread().getName());
                    next.notify();
                }
                try {
                    if (i == 9) {
                        return;     // 当i == 9 即最后一次循环, 将直接退出 不再进行等待                   
                    } 
 
                    self.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
 
    }
}