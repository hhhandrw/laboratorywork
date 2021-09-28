package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testApply() {
        ConstantFunction constantFunction = new ConstantFunction(5);

        assertEquals(constantFunction.apply(3), 5.0);
        assertEquals(constantFunction.apply(0), 5.0);
        assertEquals(constantFunction.apply(-5), 5.0);
    }
}