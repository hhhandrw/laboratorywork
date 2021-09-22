package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        IdentityFunction IF = new IdentityFunction();
        SqrFunction SF = new SqrFunction();
        TanFunction TF = new TanFunction();
        HalfFunction HF = new HalfFunction();
        CompositeFunction firstCF = new CompositeFunction(IF, SF);
        CompositeFunction secondCF = new CompositeFunction(firstCF, TF);
        CompositeFunction thirdCF = new CompositeFunction(HF, secondCF);

        assertEquals(firstCF.apply(5), 25.0);
        assertEquals(firstCF.apply(9), 81.0);
        assertEquals(secondCF.apply(2), 1.1578212823495775);
        assertEquals(secondCF.apply(10), -0.5872139151569291);
        assertEquals(thirdCF.apply(4), 1.1578212823495775);
        assertEquals(thirdCF.apply(3), -1.2386276162240966);
    }
}