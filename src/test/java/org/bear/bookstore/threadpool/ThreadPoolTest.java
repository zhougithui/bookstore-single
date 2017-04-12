package org.bear.bookstore.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
     public static void main(String[] args) { 
    	 /**
    	  * int corePoolSize 最小线程数
    	  * int maximumPoolSize 最大线程数
    	  * long keepAliveTime 存活时间
    	  * TimeUnit unit 时间单位
    	  * BlockingQueue<Runnable> workQueue 任务队列
    	  * LargestPoolSize 介于corePoolSize和maximumPoolSize之间，受workQueue队列大小的影响
    	  * 
    	  * 如下设置：由于任务未能及时的执行完毕，所以队列会存在5个任务，会创建10个线程去执行任务，如果队列设置为4，则会出现拒绝服务
    	  */
         ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                 new ArrayBlockingQueue<Runnable>(3));
          
         for(int i=0;i<15;i++){
             MyTask myTask = new MyTask(i);
             executor.execute(myTask);
             System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
             executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount() + "，历史线程池：" + executor.getLargestPoolSize());
         }
         executor.shutdown();
     }
}
 
 
class MyTask implements Runnable {
    private int taskNum;
     
    public MyTask(int num) {
        this.taskNum = num;
    }
     
    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}