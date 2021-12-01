package ru.ssau.tk.respect.laboratorywork1.operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.respect.laboratorywork1.functions.*;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.TabulatedFunctionFactory;

import static org.testng.Assert.*;
import static ru.ssau.tk.respect.laboratorywork1.operations.TabulatedFunctionOperationService.asPoints;

public class TabulatedFunctionOperationServiceTest {

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValuesList = new double[]{4, 6, 8, 10, 12};
    private final double[] yValuesArray = new double[]{2, 6, 10, 14, 18};

    private final MathFunction function = new HalfFunction();

    private TabulatedFunction createFunction() {
        return new LinkedListTabulatedFunction(function, 2, 6, 9);
    }

    private TabulatedFunction createLinkedListTabulatedFromArray() {
        return new LinkedListTabulatedFunction(xValues, yValuesList);
    }

    private TabulatedFunction createArrayTabulatedFromArray() {
        return new LinkedListTabulatedFunction(xValues, yValuesArray);
    }

    @Test
    public void testAsPoints() {
        TabulatedFunction firstFunction = createFunction();
        TabulatedFunction secondFunction = createLinkedListTabulatedFromArray();
        Point[] firstPoints = asPoints(firstFunction);
        Point[] secondPoints = asPoints(secondFunction);

        int i = 0;
        for (Point point : firstPoints) {
            assertEquals(firstFunction.getX(i), point.x);
            assertEquals(firstFunction.getY(i++), point.y);
        }

        int j = 0;
        for (Point point : secondPoints) {
            assertEquals(secondFunction.getX(j), point.x);
            assertEquals(secondFunction.getY(j++), point.y);
        }
    }

    @Test
    public void testMultiply() {
        TabulatedFunction linkedListTabulatedFunction = createLinkedListTabulatedFromArray();

        double[] xValuesFailOne = new double[]{1, 2, 3};
        double[] yValuesFailOne = new double[]{2, 4, 6};
        TabulatedFunction linkedListTabulatedFunctionFailOne = new LinkedListTabulatedFunctionFactory().create(xValuesFailOne, yValuesFailOne);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().multiply(linkedListTabulatedFunctionFailOne, linkedListTabulatedFunction));
        TabulatedFunction arrayTabulatedFunctionFailOne = new ArrayTabulatedFunctionFactory().create(xValuesFailOne, yValuesFailOne);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().multiply(arrayTabulatedFunctionFailOne, linkedListTabulatedFunction));

        double[] xValuesFailTwo = new double[]{1, 3, 5, 7, 10};
        double[] yValuesFailTwo = new double[]{4, 6, 8, 10, 12};
        TabulatedFunction linkedListTabulatedFunctionFailTwo = new LinkedListTabulatedFunctionFactory().create(xValuesFailTwo, yValuesFailTwo);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().multiply(linkedListTabulatedFunctionFailTwo, linkedListTabulatedFunction));
        TabulatedFunction arrayTabulatedFunctionFailTwo = new ArrayTabulatedFunctionFactory().create(xValuesFailTwo, yValuesFailTwo);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().multiply(arrayTabulatedFunctionFailTwo, linkedListTabulatedFunction));

        TabulatedFunction linkedLIstMultiplication = new TabulatedFunctionOperationService().multiply(linkedListTabulatedFunction, linkedListTabulatedFunction);
        int i = 0;
        for (Point point : linkedLIstMultiplication) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] * yValuesList[i++]);
        }
        assertTrue(linkedLIstMultiplication instanceof ArrayTabulatedFunction);

        TabulatedFunction arrayTabulatedFunction = createArrayTabulatedFromArray();

        TabulatedFunction listAndArrayMultiplicationOne = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).multiply(linkedListTabulatedFunction, arrayTabulatedFunction);
        i = 0;
        for (Point point : listAndArrayMultiplicationOne) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] * yValuesList[i++]);
        }
        assertTrue(listAndArrayMultiplicationOne instanceof LinkedListTabulatedFunction);

        TabulatedFunction listAndArrayMultiplicationTwo = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory()).multiply(linkedListTabulatedFunction, arrayTabulatedFunction);
        i = 0;
        for (Point point : listAndArrayMultiplicationTwo) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] * yValuesList[i++]);
        }
        assertTrue(listAndArrayMultiplicationTwo instanceof ArrayTabulatedFunction);
    }

    @Test
    public void testDivide() {
        TabulatedFunction linkedListTabulatedFunction = createLinkedListTabulatedFromArray();

        double[] xValuesFailOne = new double[]{1, 2, 3};
        double[] yValuesFailOne = new double[]{2, 4, 6};
        TabulatedFunction linkedListTabulatedFunctionFailOne = new LinkedListTabulatedFunctionFactory().create(xValuesFailOne, yValuesFailOne);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().divide(linkedListTabulatedFunctionFailOne, linkedListTabulatedFunction));
        TabulatedFunction arrayTabulatedFunctionFailOne = new ArrayTabulatedFunctionFactory().create(xValuesFailOne, yValuesFailOne);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().divide(arrayTabulatedFunctionFailOne, linkedListTabulatedFunction));

        double[] xValuesFailTwo = new double[]{1, 3, 5, 7, 10};
        double[] yValuesFailTwo = new double[]{4, 6, 8, 10, 12};
        TabulatedFunction linkedListTabulatedFunctionFailTwo = new LinkedListTabulatedFunctionFactory().create(xValuesFailTwo, yValuesFailTwo);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().divide(linkedListTabulatedFunctionFailTwo, linkedListTabulatedFunction));
        TabulatedFunction arrayTabulatedFunctionFailTwo = new ArrayTabulatedFunctionFactory().create(xValuesFailTwo, yValuesFailTwo);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().divide(arrayTabulatedFunctionFailTwo, linkedListTabulatedFunction));

        TabulatedFunction linkedLIstDivision = new TabulatedFunctionOperationService().divide(linkedListTabulatedFunction, linkedListTabulatedFunction);
        int i = 0;
        for (Point point : linkedLIstDivision) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] / yValuesList[i++]);
        }
        assertTrue(linkedLIstDivision instanceof ArrayTabulatedFunction);

        TabulatedFunction arrayTabulatedFunction = createArrayTabulatedFromArray();

        TabulatedFunction listAndArrayDivisionOne = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).divide(linkedListTabulatedFunction, arrayTabulatedFunction);
        i = 0;
        for (Point point : listAndArrayDivisionOne) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] / yValuesArray[i++]);
        }
        assertTrue(listAndArrayDivisionOne instanceof LinkedListTabulatedFunction);

        TabulatedFunction listAndArrayDivisionTwo = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory()).divide(arrayTabulatedFunction, linkedListTabulatedFunction);
        i = 0;
        for (Point point : listAndArrayDivisionTwo) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] / yValuesList[i++]);
        }
        assertTrue(listAndArrayDivisionTwo instanceof ArrayTabulatedFunction);
    }

    @Test
    public void testSummarize() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction result = service.summarize(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-1, -2, -3}));
        Assert.assertEquals(result.getX(0), 1.0);
        Assert.assertEquals(result.getX(1), 2.0);
        Assert.assertEquals(result.getX(2), 3.0);
        Assert.assertEquals(result.getY(0), 0.0);
        Assert.assertEquals(result.getY(1), 0.0);
        Assert.assertEquals(result.getY(2), 0.0);
        Assert.assertTrue(result instanceof LinkedListTabulatedFunction);
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.summarize(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 4}, new double[]{-1, -2, -3})));
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.summarize(arrayFactory.create(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-1, -2, -3})));
    }

    @Test
    public void testSubtract() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction result = service.subtract(arrayFactory.create(new double[]{1, 2, 3}, new double[]{5, 2, 3}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-4, -7, -6}));
        Assert.assertEquals(result.getX(0), 1.0);
        Assert.assertEquals(result.getX(1), 2.0);
        Assert.assertEquals(result.getX(2), 3.0);
        Assert.assertEquals(result.getY(0), 9.0);
        Assert.assertEquals(result.getY(1), 9.0);
        Assert.assertEquals(result.getY(2), 9.0);
        Assert.assertTrue(result instanceof LinkedListTabulatedFunction);
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.subtract(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 4}, new double[]{-1, -2, -3})));
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.subtract(arrayFactory.create(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-1, -2, -3})));
    }
}

