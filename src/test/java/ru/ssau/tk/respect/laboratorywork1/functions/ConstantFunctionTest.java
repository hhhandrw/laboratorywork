package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    @Test
    public void testApply() {
        ConstantFunction c = new ConstantFunction(5);

        assertEquals(c.apply(3), 5.0);

        assertEquals(c.apply(0), 5.0);

        assertEquals(c.apply(-5), 5.0);
    }
}