package org.bear.bookstore.test.task;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * spring的task框架异常处理器
 * @author q
 *
 */
@Slf4j
public class BookStoreAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		log.error(ex.getMessage());
		log.error(method.getName());
		for(int i=params.length-1; i>=0;i--){
			log.error(params[i].toString());
		}
	}

}
