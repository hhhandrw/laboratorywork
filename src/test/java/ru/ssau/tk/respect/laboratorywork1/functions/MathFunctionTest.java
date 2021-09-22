package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {

    @Test
    public void testAndThen() {
        MathFunction sf = new SqrFunction();
        MathFunction tf = new TanFunction();
        MathFunction id = new IdentityFunction();

        assertEquals(sf.andThen(tf).andThen(id).apply(1),
                1.5574077246549023);
        assertEquals(tf.andThen(sf).andThen(id).apply(8),
                46.23607587425524);
        assertEquals(id.andThen(tf).andThen(sf).apply(17),
                12.207446537693869);
        assertEquals(id.andThen(sf).andThen(tf).apply(10),
                -0.5872139151569291);
    }

}
