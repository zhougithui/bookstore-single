package org.bear.bookstore.common.intercepter;

import java.lang.reflect.Method;

import org.bear.bookstore.common.annotation.DbKey;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;

public class DBSwitchBeforeAdvice implements MethodBeforeAdvice {
	 public void before(Method m, Object[] args, Object target) throws Throwable {
		 DbKey key = AnnotationUtils.findAnnotation(m, DbKey.class);
		 if(!ObjectUtils.isEmpty(key)){
			 System.out.println(key.value());
		 }
    }
}
