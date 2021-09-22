package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HalfFunctionTest {

    @Test
    public void testApply() {

        HalfFunction halfFunction = new HalfFunction();

        assertEquals(halfFunction.apply(4), 2.0);
        assertEquals(halfFunction.apply(3), 1.5);
        assertEquals(halfFunction.apply(100), 50.0);
        assertEquals(halfFunction.apply(-5), -2.5);
    }
}