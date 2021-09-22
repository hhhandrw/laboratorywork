package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.math.*;

public class TanFunctionTest {

    @Test
    public void testApply() {

        TanFunction function = new TanFunction();

        assertEquals(function.apply(2), -2.185039863261519);
        assertEquals(function.apply(56), -0.6112736881917098);
        assertEquals(function.apply(-108), -2.468161961582769);
    }
}