package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    @Test
    public void testApply() {
        ZeroFunction zeroFunction = new ZeroFunction();

        assertEquals(zeroFunction.apply(11), 0.0);
        assertEquals(zeroFunction.apply(0), 0.0);
        assertEquals(zeroFunction.apply(678), 0.0);
    }
}