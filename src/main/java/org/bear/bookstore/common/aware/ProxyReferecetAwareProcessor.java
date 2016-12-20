package org.bear.bookstore.common.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 自动注入自己的代理对象
 * beanpostprocessor为
 * AbstractApplicationContext.registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory)
 * PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
 * @author q
 *
 */
public class ProxyReferecetAwareProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * 必须是初始化完毕之后注入
	 * 因为创建代理是在
	 * @see org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator
	 * 的postProcessAfterInitialization方法中进行
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof ProxyReferenceAware){
			((ProxyReferenceAware)bean).setProxyReferece(bean);
		}
		return bean;
	}

}
