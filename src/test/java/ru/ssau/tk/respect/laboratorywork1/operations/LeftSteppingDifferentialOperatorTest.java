package ru.ssau.tk.respect.laboratorywork1.operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.functions.HalfFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public static void testDerive() {
        LeftSteppingDifferentialOperator leftSteppingDifferentialOperator = new LeftSteppingDifferentialOperator(5);
        Assert.assertEquals(leftSteppingDifferentialOperator.derive(new HalfFunction()).apply(9), 0.5);
        Assert.assertThrows(IllegalArgumentException.class, () -> leftSteppingDifferentialOperator.setStep(-5.0));
        Assert.assertThrows(IllegalArgumentException.class, () -> new LeftSteppingDifferentialOperator(Double.NaN));
    }
}