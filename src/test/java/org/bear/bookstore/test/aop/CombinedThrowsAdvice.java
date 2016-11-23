package org.bear.bookstore.test.aop;

import java.lang.reflect.Method;
import java.rmi.RemoteException;

import javax.servlet.ServletException;

import org.springframework.aop.ThrowsAdvice;

public class CombinedThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(RemoteException ex) throws Throwable {
        // Do something with remote exception
    }

    public void afterThrowing(Method m, Object[] args, Object target, ServletException ex) {
        // Do something with all arguments
    }
}