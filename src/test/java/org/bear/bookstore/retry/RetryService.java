package org.bear.bookstore.retry;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

class RetryService {
    @Retryable(RemoteAccessException.class)
    public void service(String id) {
        // ... do something
    	System.out.println("do service ...." + id);
    	throw new RemoteAccessException("调用api接口异常。。。。。。");
    }
    @Recover
    public void recover(RemoteAccessException e) {
    	// ... panic
    	System.out.println("recover....");
    }
}