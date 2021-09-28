package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {

    @Test
    public void testApply() {
        UnitFunction unitFunction = new UnitFunction();

        assertEquals(unitFunction.apply(101), 1.0);
        assertEquals(unitFunction.apply(0), 1.0);
        assertEquals(unitFunction.apply(-101), 1.0);
    }
}