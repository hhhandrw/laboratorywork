package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        SqrFunction sqrFunction = new SqrFunction();

        assertEquals(sqrFunction.apply(5), 25.0);
        assertEquals(sqrFunction.apply(0), 0.0);
        assertEquals(sqrFunction.apply(-12), 144.0);
    }
}