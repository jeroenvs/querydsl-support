package org.jeroen.querydsl;

import static org.junit.Assert.assertEquals;

import org.jeroen.querydsl.domain.Car;
import org.junit.Before;
import org.junit.Test;

public class PathBasedPropertyAccessorTest {
    private Car car;
    
    @Before
    public void setUpCar() {
        car = new Car();
        car.setModel("Mercedes SLR");
        car.setHorsePower(623);
    }
    
    @Test
    public void testDirectProperty() {
        assertEquals(Integer.valueOf(623), car.getHorsePower());
    }
    
}
