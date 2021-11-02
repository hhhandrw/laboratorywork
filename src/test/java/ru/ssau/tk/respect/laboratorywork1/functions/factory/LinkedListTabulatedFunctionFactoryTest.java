package ru.ssau.tk.respect.laboratorywork1.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.functions.LinkedListTabulatedFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {
    @Test
    public void testCreate() {
        double[] xValues = new double[]{1, 3, 5, 7, 9};
        double[] yValues = new double[]{4, 6, 8, 10, 12};

        assertTrue(new LinkedListTabulatedFunctionFactory().create(xValues, yValues) instanceof LinkedListTabulatedFunction);
    }
}