package org.jeroen.querydsl;

import static java.lang.String.format;

import com.mysema.query.types.Path;

public class PathBasedPropertyAccessor {
    private final PropertyAccessor propertyAccessor;
    
    public PathBasedPropertyAccessor() {
        this(new SimplePropertyAccessor());
    }
    
    public PathBasedPropertyAccessor(PropertyAccessor propertyAccessor) {
        this.propertyAccessor = propertyAccessor;
    }

    public <T> T getPropertyValue(Object bean, Path<T> path) {
        checkBeanWithPathRoot(bean, path);
        
        // Retrieve property container recursively
        if(!path.getMetadata().isRoot()) {
            bean = getPropertyValue(bean, path.getMetadata().getParent());
        }

        Object result = null;
        if(bean != null) {
            switch(path.getMetadata().getPathType()) {
            case PROPERTY:
                result = propertyAccessor.getPropertyValue(bean, expressionOf(path));
                break;
            case VARIABLE:
                result = bean;
                break;
            }
        }
        return (T) result;
    }
    
    private static String expressionOf(Path<?> path) {
        return path.getMetadata().getExpression().toString();
    }
    
    private static void checkBeanWithPathRoot(Object bean, Path<?> path) {
        Class<?> rootClass = path.getRoot().getType();
        if(! rootClass.isAssignableFrom(bean.getClass()) ) {
            throw new IllegalArgumentException(format("Path root type '%s' does not match bean '%s'.", rootClass, bean));
        }
    }
    
}
