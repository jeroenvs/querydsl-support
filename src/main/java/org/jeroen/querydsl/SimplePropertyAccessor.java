package org.jeroen.querydsl;

import org.apache.commons.beanutils.PropertyUtils;

public class SimplePropertyAccessor implements PropertyAccessor {
    
    @Override
    public Object getPropertyValue(Object bean, String propertyName) {
        try {
            return PropertyUtils.getProperty(bean, propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
