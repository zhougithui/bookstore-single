package org.bear.bookstore.thread;

import java.io.IOException;

class MyThread1 extends Thread {
    
    public MyThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        int i=0;
        while (!isInterrupted()) {
            try {
                Thread.sleep(100); // 休眠100ms
            } catch (InterruptedException ie) {  
                System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException.");  
            }
            i++;
            System.out.println(Thread.currentThread().getName()+" ("+this.getState()+") loop " + i);  
        }
    }
}

public class Demo2 {

    public static void main(String[] args) throws IOException {  
        try {  
            Thread t1 = new MyThread1("t1");  // 新建“线程t1”
            System.out.println(t1.getName() +" ("+t1.getState()+") is new.");  

            t1.start();                      // 启动“线程t1”
            System.out.println(t1.getName() +" ("+t1.getState()+") is started.");  

            // 主线程休眠300ms，然后主线程给t1发“中断”指令。
            Thread.sleep(300);
            t1.interrupt();
            System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted.");

            // 主线程休眠300ms，然后查看t1的状态。
            Thread.sleep(300);
            System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted now.");
        } catch (InterruptedException e) {  
            e.printStackTrace();
        }
        System.in.read();
    } 
}