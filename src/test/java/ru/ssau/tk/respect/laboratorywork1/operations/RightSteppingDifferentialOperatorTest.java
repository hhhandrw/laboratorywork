package ru.ssau.tk.respect.laboratorywork1.operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.functions.HalfFunction;

import static org.testng.Assert.*;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        RightSteppingDifferentialOperator rightSteppingDifferentialOperator = new RightSteppingDifferentialOperator(2);
        Assert.assertEquals(rightSteppingDifferentialOperator.derive(new HalfFunction()).apply(5), 0.5);
        Assert.assertThrows(IllegalArgumentException.class, () -> rightSteppingDifferentialOperator.setStep(Double.NaN));
        Assert.assertThrows(IllegalArgumentException.class, () -> new RightSteppingDifferentialOperator(Double.POSITIVE_INFINITY));
    }
}