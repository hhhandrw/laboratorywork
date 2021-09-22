package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        IdentityFunction identityFunction = new IdentityFunction();

        assertEquals(identityFunction.apply(5), 5.0);
        assertEquals(identityFunction.apply(0), 0.0);
        assertEquals(identityFunction.apply(-14), -14.0);
        assertEquals(identityFunction.apply(1 / 20.0), 0.05);
    }
}