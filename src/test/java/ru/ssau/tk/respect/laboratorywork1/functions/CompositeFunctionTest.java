package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        IdentityFunction IF = new IdentityFunction();
        SqrFunction SF = new SqrFunction();
        TanFunction TF = new TanFunction();
        HalfFunction HF = new HalfFunction();
        CompositeFunction firstCF = new CompositeFunction(IF, SF);
        CompositeFunction secondCF = new CompositeFunction(firstCF, TF);
        CompositeFunction thirdCF = new CompositeFunction(HF, secondCF);

        assertEquals(firstCF.apply(5), 25.0, DELTA);
        assertEquals(firstCF.apply(9), 81.0, DELTA);
        assertEquals(secondCF.apply(2), 1.1578, DELTA);
        assertEquals(secondCF.apply(10), -0.5872, DELTA);
        assertEquals(thirdCF.apply(4), 1.1578, DELTA);
        assertEquals(thirdCF.apply(3), 0.0392, DELTA);
    }

}