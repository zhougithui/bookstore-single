package org.bear.bookstore;

import java.awt.Button;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
	private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    private final static AtomicInteger ctl = new AtomicInteger(0 | RUNNING);
	public static void main(String[] args) {
		/*System.out.println(ctl.get() & CAPACITY);
		
		System.out.println(ctl.get() & ~CAPACITY);*/
		
		//new SubGreeter().greet();
		
		/*List<String> labels = new ArrayList<>();
		Stream<Button> stream = labels.stream().map(Button::new);
		List<Button> buttons = stream.collect(Collectors.toList());*/
		/*String[] arr = {"afda","fda"};
		Arrays.sort(arr, (a, b) -> {
			System.out.println(Thread.currentThread().getName());
			return a.compareTo(b);
		});
		System.out.println(Thread.currentThread().getName());*/
		
		/*Path path = Paths.get("D:/工作文件");
		File file = path.toFile();*/
		/*File[] files = file.listFiles((File name) -> {
			return name.isDirectory();
		});
		files = file.listFiles(File::isDirectory);
		for(File f : files){
			System.out.println(f.getName());
		}*/
		
		/*new Thread(uncheck(()->{
			Thread.sleep(100);
			System.out.println("fdafdasfdsa");
			return null;
		})).start();*/
		
		
		/*String[] names = {"lili", "pawl"};
		List<Runnable> list = new ArrayList<>();
		for(String name : names){
			list.add(()->{System.out.println(name);});
		}
		for(Runnable r : list){
			new Thread(r).start();;
		}*/
		//System.out.println(f(2));
		
		System.out.println("{\"fdsa\"}".replace("\"", ""));
	}
	public static int f(int n){
		if(n == 0 || n == 1)
			return 1;
		int f0 = 1;
		int f1 = 1;
		int count = 0;
		for(int i=2; i<= n; i++){
			count = f0 + f1;
			f0 = f1;
			f1 = count;
		}
		return count;
	}
	/*public static Runnable uncheck(RunnableEx runner){
		return () -> {
			try {
				runner.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}*/
	public static Runnable uncheck(Callable<Void> runner){
		return () -> {
			try {
				runner.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
	
}
@FunctionalInterface
interface RunnableEx {
	public void run() throws Exception;
}

class Greeter{
	public void greet(){
		System.out.println("f greeter..");
	}
}
class SubGreeter extends Greeter{
	@Override
	public void greet() {
		new Thread(super::greet).start();
	}
}

interface Collections<T> extends Collection<T>{
	void forEachIf(Consumer<T> action, Predicate<T> filter);
}