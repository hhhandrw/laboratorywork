package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        SqrFunction function = new SqrFunction();

        assertEquals(function.apply(5), 25.0);
        assertEquals(function.apply(0), 0.0);
        assertEquals(function.apply(-12), 144.0);
    }
}