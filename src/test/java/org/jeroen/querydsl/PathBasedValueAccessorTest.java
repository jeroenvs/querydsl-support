package org.jeroen.querydsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.jeroen.querydsl.domain.Car;
import org.jeroen.querydsl.domain.Person;
import org.jeroen.querydsl.domain.QCar;
import org.junit.Before;
import org.junit.Test;

public class PathBasedValueAccessorTest {
    private static final QCar $ = QCar.car;
    
    private PathBasedValueAccessor accessor;

    @Before
    public void setUpAccessor() {
        accessor = new PathBasedValueAccessor();
    }
    
    private Car car;
    
    @Before
    public void setUpCar() {
        car = new Car();
        car.setModel("Mercedes SLR");
        car.setHorsePower(623);
        Person jeroen = new Person();
        jeroen.setName("Jeroen");
        car.setOwner(jeroen);
    }
    
    /**
     * Read property directly from car bean.
     */
    @Test
    public void testDirectProperty() {
        Integer horsePower = accessor.getPathValue(car, $.horsePower);
        assertEquals(Integer.valueOf(623), horsePower);
    }
    
    /**
     * Read property from nested 'owner' property.
     */
    @Test
    public void testNestedProperty() {
        String ownerName = accessor.getPathValue(car, $.owner.name);
        assertEquals("Jeroen", ownerName);
    }
    
    /**
     * Whenever an element in our path is {@code null} the end
     * result should also be {@code null}, without any exceptions.
     */
    @Test
    public void testNestedPropertyWithNullParent() {
        car.setOwner(null);
        assertNull(accessor.getPathValue(car, $.owner.name));
    }
    
    /**
     * The value path starts at car, so anything that is not a
     * car should enforce an exception.
     */
    @Test
    public void testInvalidBean() {
        try {
            accessor.getPathValue("string", $.horsePower);
            fail("Expected an illegal argument exception because bean type is invalid.");
        } catch(IllegalArgumentException e) {
            assertEquals("Bean is not of path root type 'Car'.", e.getMessage());
        }
    }
    
}
