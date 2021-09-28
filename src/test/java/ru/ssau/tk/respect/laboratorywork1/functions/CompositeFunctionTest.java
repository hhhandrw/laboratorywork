package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        IdentityFunction identityFunction = new IdentityFunction();
        SqrFunction sqrFunction = new SqrFunction();
        TanFunction tanFunction = new TanFunction();
        HalfFunction halfFunction = new HalfFunction();
        CompositeFunction firstCompositeFunction = new CompositeFunction(identityFunction, sqrFunction);
        CompositeFunction secondCompositeFunction = new CompositeFunction(firstCompositeFunction, tanFunction);
        CompositeFunction thirdCompositeFunction = new CompositeFunction(halfFunction, secondCompositeFunction);

        assertEquals(firstCompositeFunction.apply(5), 25.0, DELTA);
        assertEquals(firstCompositeFunction.apply(9), 81.0, DELTA);
        assertEquals(secondCompositeFunction.apply(2), 1.1578, DELTA);
        assertEquals(secondCompositeFunction.apply(10), -0.5872, DELTA);
        assertEquals(thirdCompositeFunction.apply(4), 1.1578, DELTA);
        assertEquals(thirdCompositeFunction.apply(3), -1.2386, DELTA);
    }
}