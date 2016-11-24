package org.bear.bookstore.test.aspectj.example1;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Advice implements Ordered {
    private int order = 1;

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /**
	 * Callback before a given method is invoked.
	 * @param method method being invoked
	 * @param args arguments to the method
	 * @param target target of the method invocation. May be {@code null}.
	 * @throws Throwable if this object wishes to abort the call.
	 * Any exception thrown will be returned to the caller if it's
	 * allowed by the method signature. Otherwise the exception
	 * will be wrapped as a runtime exception.
	 */
    @Before("execution(* org.bear.bookstore.test.aspectj.example1..*.*(..)) &&" +
    		"@annotation(org.bear.bookstore.test.aspectj.example1.Idempotent)")
	public void before(JoinPoint joinPoint) throws Throwable{
    	/**
		 * Object[] getArgs：返回目标方法的参数
		 * Signature getSignature：返回目标方法的签名 
		 * Object getTarget：返回被织入增强处理的目标对象 
		 * Object getThis：返回AOP框架为目标对象生成的代理对象
		 */
    	log.error("before advice invoke........");
    	log.error(joinPoint.getTarget().getClass().getName());
    }

}