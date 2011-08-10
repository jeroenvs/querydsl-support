package org.jeroen.querydsl;

public interface PropertyAccessor {
    Object getPropertyValue(Object bean, String propertyName);
}
