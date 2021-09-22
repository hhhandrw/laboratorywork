package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    @Test
    public void testApply() {

        UnitFunction u = new UnitFunction();

        assertEquals(u.apply(101), 1.0);

        assertEquals(u.apply(0), 1.0);

        assertEquals(u.apply(-101), 1.0);
    }
}