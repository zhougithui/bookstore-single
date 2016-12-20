package org.bear.bookstore.common.aware;

import org.springframework.beans.factory.Aware;

/**
 * spring提供的aware抽象，通过beanpostprocessor实现
 * @see ProxyReferecetAwareProcessor
 * @author q
 *
 */
public interface ProxyReferenceAware extends Aware {
	void setProxyReferece(Object proxy);
}
