package ru.ssau.tk.respect.laboratorywork1.operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.functions.HalfFunction;

import static org.testng.Assert.*;

public class MiddleSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        MiddleSteppingDifferentialOperator middleSteppingDifferentialOperator = new MiddleSteppingDifferentialOperator(3);
        Assert.assertEquals(middleSteppingDifferentialOperator.derive(new HalfFunction()).apply(10), 0.5);
        Assert.assertThrows(IllegalArgumentException.class, () -> middleSteppingDifferentialOperator.setStep(Double.NaN));
        Assert.assertThrows(IllegalArgumentException.class, () -> new MiddleSteppingDifferentialOperator(Double.POSITIVE_INFINITY));
    }
}