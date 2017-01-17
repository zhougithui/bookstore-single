package org.bear.bookstore.reactor;

import reactor.fn.BiConsumer;
import reactor.fn.Consumer;
import reactor.fn.Function;
import reactor.fn.Supplier;
import reactor.fn.tuple.Tuple;
import reactor.fn.tuple.Tuple2;

public class R3 {
	public static void main(String[] args) {
		Consumer<String> consumer = (data -> {
			System.out.println(data);
		});
		
		Function<Integer, String> transformation = (data -> {
			return "data is " + data;
		});
		
		Supplier<Integer> supplier = (() -> {
			return 123;
		});
		
		BiConsumer<Consumer<String>, String> biConsumer = 
				((callback, value) -> {
					for(int i = 0; i < 10; i++){
			            // 对要运行的最后逻辑运行做惰性求值
			            callback.accept(value + "-" + i);
				    }
				});
		
		//生产者到双向消费者执行过程
		biConsumer.accept(consumer, transformation.apply(supplier.get()));
		
		System.out.println("------------------------------------");
		Consumer<Tuple2<Consumer<String>, String>> biConsumer1 = 
				((tuple -> {
					for(int i = 0; i < 10; i++){        
		                // 类型正确，开启编译器
		                tuple.getT1().accept(tuple.getT2() + "-" + i);
					}
				}));
		biConsumer1.accept(Tuple.of(consumer, transformation.apply(supplier.get())));
	}
}
