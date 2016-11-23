package org.bear.bookstore.test.propagation.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Nested2 {
	@Autowired Nested nested;
	@Autowired JdbcTemplate jdbcTemplate;
	
	@Transactional(
			propagation=Propagation.REQUIRED,
			rollbackFor={Exception.class}
			)
	public void a(){
		/**
		 * 如果aa（）抛出异常，b未出现异常，则a会回滚b的结果
		 */
		aa();
		try {
			/**
			 * 调用b，捕获异常，异常不会冒泡，aa()执行成功
			 * 也就是nested的用法，在一个类中这么调用没有效果，如调用aaa（）
			 */
			nested.b();
			
			/**
			 * 如果调用a则会出现如下异常，
			 * org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
					at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:724)
					at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:504)
					at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:292)
					at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)
					at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
					at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:655)
					at org.bear.bookstore.test.propagation.jdbc.Nested2$$EnhancerBySpringCGLIB$$567ca65b.a(<generated>)
					at org.bear.bookstore.test.propagation.JdbcTransactionPropagationTest.NestedTest(JdbcTransactionPropagationTest.java:25)
					at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
					at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
					at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
					at java.lang.reflect.Method.invoke(Unknown Source)
					at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
					at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
					at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
					at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
					at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
					at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
					at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
					at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
					at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:252)
					at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:94)
					at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
					at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
					at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
					at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
					at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
					at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
					at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
					at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
					at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:191)
					at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
					at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
					at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
					at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:678)
					at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
					at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)
			 */
			//nested.a();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional(
			propagation=Propagation.REQUIRED,
			rollbackFor={Exception.class}
			)
	public void aa(){
		jdbcTemplate.execute("insert into custom(address,cusname,email,phone,sex) values('北京市，朝阳区，十里河村','zhouhui','zh@qq.com','15555555555','1')");
		/*int x = 1/0;
		System.out.println(x);*/
	}
	
	
	/**
	 * 这样的结果是
	 * 	如果bbb是propagation=Propagation.NESTED，则两个custom都保存成功
	 * 	如果bbb是propagation=Propagation.REQUIRED，则两个custom都保存成功
	 */
	@Transactional(
			propagation=Propagation.REQUIRED,
			rollbackFor={Exception.class}
			)
	public void aaa(){
		jdbcTemplate.execute("insert into custom(address,cusname,email,phone,sex) values('北京市，朝阳区，十里河村','zhouhui','zh@qq.com','15555555555','1')");
		try {
			bbb();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Transactional(
			//propagation=Propagation.NESTED,
			propagation=Propagation.REQUIRED,
			rollbackFor={Exception.class}
			)
	public void bbb(){
		jdbcTemplate.execute("insert into custom(address,cusname,email,phone,sex) values('北京市，朝阳区，十里河村','zhouhui','zh@qq.com','15555555555','1')");
		int x = 1/0;
		System.out.println(x);
	}
	
}
