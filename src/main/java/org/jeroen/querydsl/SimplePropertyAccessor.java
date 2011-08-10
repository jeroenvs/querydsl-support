package org.jeroen.querydsl;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Simple implementation that uses bean utils.
 * 
 * @see org.apache.commons.beanutils.PropertyUtils
 * @author Jeroen van Schagen
 * @since 20-08-2011
 */
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
