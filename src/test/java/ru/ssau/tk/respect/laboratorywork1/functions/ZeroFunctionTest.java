package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    @Test
    public void testApply() {
        ZeroFunction z = new ZeroFunction();

        assertEquals(z.apply(11), 0.0);
        assertEquals(z.apply(0), 0.0);
        assertEquals(z.apply(678), 0.0);
    }

}