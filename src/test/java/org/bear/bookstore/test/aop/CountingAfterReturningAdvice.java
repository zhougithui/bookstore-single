package org.bear.bookstore.test.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class CountingAfterReturningAdvice implements AfterReturningAdvice {

    private int count;

    public void afterReturning(Object returnValue, Method m, Object[] args,
            Object target) throws Throwable {
        ++count;
    }

    public int getCount() {
        return count;
    }

}