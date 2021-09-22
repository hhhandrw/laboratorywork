package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testAndThen() {
        MathFunction sf = new SqrFunction();
        MathFunction tf = new TanFunction();
        MathFunction id = new IdentityFunction();

        assertEquals(sf.andThen(tf).andThen(id).apply(1),
                1.5574, DELTA);
        assertEquals(tf.andThen(sf).andThen(id).apply(8),
                46.2360, DELTA);
        assertEquals(id.andThen(tf).andThen(sf).apply(17),
                12.2074, DELTA);
        assertEquals(id.andThen(sf).andThen(tf).apply(10),
                -0.5872, DELTA);
    }

}
