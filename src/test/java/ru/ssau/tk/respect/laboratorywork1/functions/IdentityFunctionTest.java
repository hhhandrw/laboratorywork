package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void TestFunction() {
        IdentityFunction function = new IdentityFunction();

        assertEquals(function.apply(5), 5.0);
        assertEquals(function.apply(0), 0.0);
        assertEquals(function.apply(-14), -14.0);
        assertEquals(function.apply(1 / 20.0), 0.05);
    }

}