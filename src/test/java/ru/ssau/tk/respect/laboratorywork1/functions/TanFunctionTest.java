package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TanFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        TanFunction function = new TanFunction();

        assertEquals(function.apply(2), -2.1850, DELTA);
        assertEquals(function.apply(56), -0.6112, DELTA);
        assertEquals(function.apply(-108), -2.4681, DELTA);
    }

}