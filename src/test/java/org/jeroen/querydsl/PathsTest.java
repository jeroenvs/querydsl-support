package org.jeroen.querydsl;

import static org.jeroen.querydsl.Paths.expressionOf;
import static org.jeroen.querydsl.Paths.pathToSeperatedString;
import static org.junit.Assert.assertEquals;

import org.jeroen.querydsl.domain.QCar;
import org.junit.Test;

public class PathsTest {
    private QCar $ = QCar.car;

    @Test
    public void testToSeparatedString() {
        assertEquals("owner.name", pathToSeperatedString($.owner.name));
    }
    
    @Test
    public void testNullToSeparatedString() {
        assertEquals("", pathToSeperatedString(null));
    }
    
    @Test
    public void testExpressionOf() {
        assertEquals($.horsePower.getMetadata().getExpression(), expressionOf($.horsePower));
    }
    
}
