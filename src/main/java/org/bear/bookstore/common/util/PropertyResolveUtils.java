package org.bear.bookstore.common.util;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

@Service
public class PropertyResolveUtils implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        stringValueResolver = resolver;
    }

    public String getPropertiesValue(String name) {
        name = "${" + name + "}";
        return stringValueResolver.resolveStringValue(name);
    }
}
