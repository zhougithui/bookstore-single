package org.bear.bookstore.threadpool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

public class ThreadPoolTest1 {
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		ThreadPoolExecutor threadPool = 
				new ThreadPoolExecutor(3, 3, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
		
		new Thread(()->{
			List<String> iosList = new ArrayList<>(200);
			File file = Paths.get("d:/1.txt").toFile();
			try (BufferedReader in = 
					new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
				String data = in.readLine();
				while(StringUtils.isNoneBlank(data)){
					iosList.add(data);
					if(iosList.size() >= 200){
						List<String> taskList = new ArrayList<>(300);
						taskList.addAll(iosList);
						iosList.clear();
						threadPool.execute(new IosTask(taskList));
					}
					data = in.readLine();
				}
				if(iosList.size() < 200){
					System.out.println("---------------------------");
					List<String> taskList = new ArrayList<>(300);
					taskList.addAll(iosList);
					iosList.clear();
					threadPool.execute(new IosTask(taskList));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}).start();
		
		threadPool.awaitTermination(10, TimeUnit.SECONDS);
		threadPool.shutdown();
	}
	
}
class IosTask implements Runnable{
	private List<String> taskList = new ArrayList<>();
	
	public IosTask(List<String> taskList) {
		this.taskList = taskList;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " execute task ing.....");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		System.out.println(Thread.currentThread().getName() + ":ios send msg : " + taskList.size());
	}
}

class AndroidTask implements Runnable{
	
	@Override
	public void run() {
		
	}
	
}
