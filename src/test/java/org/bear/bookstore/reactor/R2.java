package org.bear.bookstore.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.Environment;
import reactor.core.Dispatcher;
import reactor.core.processor.RingBufferProcessor;
import reactor.fn.Consumer;

public class R2 {
	public static void main(String[] args) {
		//初始化上下文，获取默认调度这
		Environment.initialize();
		
		// RingBufferDispatcher，默认带 8192 槽容量
		Dispatcher dispatcher = Environment.sharedDispatcher();
		
		//创建回调
		Consumer<Integer> c = (data -> {
			System.out.println("some data arrived:" + data);
		});
		
		//创建error回调
		Consumer<Throwable> errorHandler = (e -> {
			e.printStackTrace();
		});
		
		//异步分发数据
		dispatcher.dispatch(1234, c, errorHandler);
		
		Environment.terminate();
		
		
		//----------------------------------------------------
		//--------------响应式数据流例子------------------------
		//----------------------------------------------------
		//独立异步处理器
		RingBufferProcessor<Integer> processor = 
				RingBufferProcessor.<Integer>create();
		
		//发送数据，确保数据的安全性，知道订阅成功
		processor.onNext(1234);
		processor.onNext(4567);
		
		//消费整形数据
		processor.subscribe(new Subscriber<Integer>() {

			@Override
			public void onSubscribe(Subscription s) {
				//unbounded subscriber
				s.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(Integer t) {
				System.out.println("data arrived " + t);
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onComplete() {
				System.out.println("done !!");
			}
		});
		
		//完全关闭内部线程和调用
		processor.onComplete();
	}
}
