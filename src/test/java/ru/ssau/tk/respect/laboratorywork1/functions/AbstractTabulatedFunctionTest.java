package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.respect.laboratorywork1.exceptions.DifferentLengthOfArraysException;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    private final double[] valuesX1 = new double[]{-15, -3, -1, 0, 1, 3, 15};
    private final double[] valuesY1 = new double[]{-5, -2, -1, -0, 1, 2};

    private final double[] valuesX2 = new double[]{-15, -3, -1, 0, 1, 3, 2};
    private final double[] valuesY2 = new double[]{};

    private final double[] valuesX3 = new double[]{-15, -3, -1, 0, 1, -1};
    private final double[] valuesY3 = new double[]{-5, -2, -1, -0, 1, 2, 9};

    private final double[] valuesX4 = new double[]{0, 1, -2};
    @Test

    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(valuesX1, valuesY1));
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(valuesX2, valuesY2));
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(valuesX3, valuesY3));
        double[] valuesX = new double[]{-1, 5};
        double[] valuesY = new double[]{9, 2};
        AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(valuesX2));
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(valuesX3));
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(valuesX4));
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{-3, -3, 7, 7, 0};
            AbstractTabulatedFunction.checkSorted(valuesX);
        });
        AbstractTabulatedFunction.checkSorted(valuesX1);
    }

    @Test
    public void testToString() {
        double[] xValues = {1, 3, 5};
        double[] yValues = {4, 6, 8};
        LinkedListTabulatedFunction firstFunction = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(firstFunction.toString(), "LinkedListTabulatedFunction size = 3\n[1.0; 4.0]\n[3.0; 6.0]\n[5.0; 8.0]\n");

        MathFunction function = new HalfFunction();
        ArrayTabulatedFunction secondFunction = new ArrayTabulatedFunction(function, 2, 3, 3);
        assertEquals(secondFunction.toString(), "ArrayTabulatedFunction size = 3\n[2.0; 1.0]\n[2.5; 1.25]\n[3.0; 1.5]\n");
    }
}