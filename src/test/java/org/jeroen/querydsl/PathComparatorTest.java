package org.jeroen.querydsl;

import static org.jeroen.querydsl.PathComparator.pathComparator;
import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.jeroen.querydsl.domain.Car;
import org.jeroen.querydsl.domain.QCar;
import org.junit.Before;
import org.junit.Test;

public class PathComparatorTest {
    private Comparator<Car> comparator;
    
    @Before
    public void setUpComparator() {
        comparator = pathComparator(QCar.car.horsePower);
    }

    @Test
    public void testEqualReference() {
        Car car = new Car();
        assertEquals(0, comparator.compare(car, car));
    }
    
    @Test
    public void testSemanticallyEqual() {
        Car car = new Car();
        car.setModel("car");
        car.setHorsePower(50);
        
        Car similarCar = new Car();
        similarCar.setModel("car");
        similarCar.setHorsePower(50);
        
        assertEquals(0, comparator.compare(car, similarCar));
    }
    
    @Test
    public void testLeftIsNull() {
        assertEquals(-1, comparator.compare(null, new Car()));
    }
    
    @Test
    public void testRightIsNull() {
        assertEquals(1, comparator.compare(new Car(), null));
    }
    
    @Test
    public void testCompareOnValue() {
        Car car = new Car();
        car.setHorsePower(50);
        
        Car betterCar = new Car();
        betterCar.setHorsePower(150);
        
        assertEquals(-1, comparator.compare(car, betterCar));
    }
    
}
