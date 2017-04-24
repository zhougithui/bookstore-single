package org.bear.bookstore.threadpool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

public class MessageFileReaderUtils2 {
	public static void main(String[] args) {
		try {
			MessageFileReaderUtils2.readMessageFile("d:/1.txt");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private static BlockingQueue<String> filePathQueue = new ArrayBlockingQueue<>(10);
	private static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
	static{
		singleThreadPool.submit(new MessageFileReaderUtils2().new FileReaderTask());
	}
	
	/**
	 * 读包含ios和android信息的文件
	 * @param path 文件的绝对路径
	 * @throws InterruptedException
	 */
	public static void readMessageFile(String path) throws InterruptedException{
		filePathQueue.put(path);
	}
	
	private static ThreadPoolExecutor threadPool = 
			new ThreadPoolExecutor(3, 3, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
	
	private static Semaphore semaphore = new Semaphore(14);
	
	class FileReaderTask implements Runnable{
		
		@Override
		public void run() {
			while(true){
				try {
					String path = filePathQueue.take();
					if(StringUtils.isNoneBlank(path)){
						readFile(path);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void readFile(String absPath){
			List<String> iosList = new ArrayList<>(200);
			File file = Paths.get(absPath).toFile();
			try (BufferedReader in = 
					new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
				String data = in.readLine();
				while(StringUtils.isNoneBlank(data)){
					iosList.add(data);
					if(iosList.size() >= 200){
						List<String> taskList = new ArrayList<>(300);
						taskList.addAll(iosList);
						iosList.clear();
						
						try {
							semaphore.acquire();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						threadPool.execute(new IosTask(taskList));
					}
					data = in.readLine();
				}
				System.out.println("---------------------------");
				if(iosList.size() < 200){
					try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					List<String> taskList = new ArrayList<>(300);
					taskList.addAll(iosList);
					iosList.clear();
					threadPool.execute(new IosTask(taskList));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class IosTask implements Runnable{
		private List<String> taskList = new ArrayList<>();
		
		public IosTask(List<String> taskList) {
			this.taskList = taskList;
		}
		
		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " execute task ing.....");
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				};
				System.out.println(Thread.currentThread().getName() + ":ios send msg : " + taskList.size());
			}finally {
				semaphore.release();
			}
		}
	}
	
	class AndroidTask implements Runnable{
		
		@Override
		public void run() {
			
		}
		
	}
}

