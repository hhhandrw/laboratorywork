package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testAndThen() {
        MathFunction sqrFunction = new SqrFunction();
        MathFunction tanFunction = new TanFunction();
        MathFunction identityFunction = new IdentityFunction();

        assertEquals(sqrFunction.andThen(tanFunction).andThen(identityFunction).apply(1),
                1.5574, DELTA);
        assertEquals(tanFunction.andThen(sqrFunction).andThen(identityFunction).apply(8),
                46.2360, DELTA);
        assertEquals(identityFunction.andThen(tanFunction).andThen(sqrFunction).apply(17),
                12.2074, DELTA);
        assertEquals(identityFunction.andThen(sqrFunction).andThen(tanFunction).apply(10),
                -0.5872, DELTA);
    }
}
