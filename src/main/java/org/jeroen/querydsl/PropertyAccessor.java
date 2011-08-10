package org.jeroen.querydsl;

/**
 * Used to access the value of a bean property.
 * 
 * @author Jeroen van Schagen
 * @since 10-08-2011
 */
public interface PropertyAccessor {
    
    /**
     * Access the property value of a bean.
     * @param bean the bean containing our property
     * @param propertyName name of the property
     * @return current property value
     */
    Object getPropertyValue(Object bean, String propertyName);
    
}
