package org.jeroen.querydsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jeroen.querydsl.domain.Car;
import org.jeroen.querydsl.domain.QCar;
import org.junit.Before;
import org.junit.Test;

public class PathBasedPropertyAccessorTest {
    private static final QCar $ = QCar.car;
    
    private PathBasedPropertyAccessor propertyAccessor;

    @Before
    public void setUpAccessor() {
        propertyAccessor = new PathBasedPropertyAccessor();
    }
    
    private Car car;
    
    @Before
    public void setUpCar() {
        car = new Car();
        car.setModel("Mercedes SLR");
        car.setHorsePower(623);
    }
    
    @Test
    public void testDirectProperty() {
        Integer horsePower = propertyAccessor.getPropertyValue(car, $.horsePower);
        assertEquals(Integer.valueOf(623), horsePower);
    }
    
    @Test
    public void testInvalidBean() {
        try {
            propertyAccessor.getPropertyValue("string", $.horsePower);
            fail("Expected an illegal argument exception because bean type is invalid.");
        } catch(IllegalArgumentException e) {
            assertEquals("Bean is not of path root type 'Car'.", e.getMessage());
        }
    }
    
}
