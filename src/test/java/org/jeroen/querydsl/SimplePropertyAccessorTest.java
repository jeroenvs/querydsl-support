package org.jeroen.querydsl;

import static org.junit.Assert.assertEquals;

import org.jeroen.querydsl.domain.Car;
import org.junit.Test;

public class SimplePropertyAccessorTest {
    private PropertyAccessor propertyAccessor = new SimplePropertyAccessor();
    
    @Test
    public void testReadValue() {
        Car car = new Car();
        car.setModel("McLaren MP4-12C");
        assertEquals(car.getModel(), propertyAccessor.getPropertyValue(car, "model"));
    }
    
    // Ensure exceptions are wrapped in runtime exceptions
        
    @Test(expected = RuntimeException.class)
    public void testIllegalAccess() throws NoSuchMethodException {
        propertyAccessor.getPropertyValue(new AccessableBean(), "hidden");
    }
    
    @Test(expected = RuntimeException.class)
    public void testInvocationTarget() {
        propertyAccessor.getPropertyValue(new AccessableBean(), "exception");
    }
    
    @Test(expected = RuntimeException.class)
    public void testNoSuchMethod() {
        propertyAccessor.getPropertyValue(new AccessableBean(), "unknown");
    }
    
    public static final class AccessableBean {
        
        protected Object getHidden() {
            return "cannot be accessed";
        }
        
        public Object getException() {
            throw new RuntimeException("alright");
        }
        
    }

    
}
