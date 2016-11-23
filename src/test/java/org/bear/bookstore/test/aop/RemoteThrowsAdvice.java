package org.bear.bookstore.test.aop;

import java.rmi.RemoteException;

import org.springframework.aop.ThrowsAdvice;

public class RemoteThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(RemoteException ex) throws Throwable {
        // Do something with remote exception
    }

}