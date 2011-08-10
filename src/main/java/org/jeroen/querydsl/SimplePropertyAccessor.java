package org.jeroen.querydsl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class SimplePropertyAccessor implements PropertyAccessor {
    
    @Override
    public Object getPropertyValue(Object bean, String propertyName) {
        try {
            return PropertyUtils.getProperty(bean, propertyName);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    
}
