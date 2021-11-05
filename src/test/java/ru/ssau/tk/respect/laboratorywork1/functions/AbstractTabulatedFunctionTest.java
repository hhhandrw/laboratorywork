package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    double[] array1 = {1, 2, 3, 4, 10, 15};
    double[] array2 = {1, 2, 3, 4, 10, 15};
    double[] array3 = {2, 1, 6, 10, 6};

    @Test
    public void testCheckLengthIsTheSame() {

    }

    @Test
    public void testCheckSorted() {

    }

    @Test
    public void testToString() {
        double[] xValues = {1, 3, 5};
        double[] yValues = {4, 6, 8};
        LinkedListTabulatedFunction firstFunction = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(firstFunction.toString(),"LinkedListTabulatedFunction size = 3\n[1.0; 4.0]\n[3.0; 6.0]\n[5.0; 8.0]\n");

        MathFunction function = new HalfFunction();
        ArrayTabulatedFunction secondFunction = new ArrayTabulatedFunction(function, 2, 3, 3);
        assertEquals(secondFunction.toString(),"ArrayTabulatedFunction size = 3\n[2.0; 1.0]\n[2.5; 1.25]\n[3.0; 1.5]\n");
    }
}