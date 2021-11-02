package ru.ssau.tk.respect.laboratorywork1.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.functions.*;

import java.util.Iterator;

import static org.testng.Assert.*;
import static ru.ssau.tk.respect.laboratorywork1.operations.TabulatedFunctionOperationService.asPoints;

public class TabulatedFunctionOperationServiceTest {

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{4, 6, 8, 10, 12};

    private final MathFunction function = new HalfFunction();

    private TabulatedFunction createFunction() {
        return new LinkedListTabulatedFunction(function, 2, 6, 9);
    }

    private TabulatedFunction createFromArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testAsPoints() {
        TabulatedFunction firstFunction = createFunction();
        TabulatedFunction secondFunction = createFromArray();
        Point[] firstPoints = asPoints(firstFunction);
        Point[] secondPoints = asPoints(secondFunction);

        int i = 0;
        for (Point point : firstPoints) {
            assertEquals(firstFunction.getX(i), point.x);
            assertEquals(firstFunction.getY(i++), point.y);
        }

        int j = 0;
        for (Point point : firstPoints) {
            assertEquals(firstFunction.getX(j), point.x);
            assertEquals(firstFunction.getY(j++), point.y);
        }
    }
}