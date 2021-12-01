package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.respect.laboratorywork1.exceptions.DifferentLengthOfArraysException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    private static final double[] xValues = new double[]{4.3, 5.4, 6, 6.4};
    private static final double[] yValues = new double[]{-2.7, 1.4, 3, 5.1};

    private final MathFunction function = new HalfFunction();

    private static final ArrayTabulatedFunction arrayTabulatedObject = new ArrayTabulatedFunction(xValues, yValues);

    private TabulatedFunction createFirstFunction() {
        return new ArrayTabulatedFunction(function, 2, 6, 9);
    }

    private TabulatedFunction createSecondFunction() {
        return new ArrayTabulatedFunction(function, -5, 4, 19);
    }

    private TabulatedFunction createThirdFunction() {
        return new ArrayTabulatedFunction(function, 13, 20, 70);
    }

    private TabulatedFunction createFromArray() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testConstructorExceptions() {
        double[] xValues = new double[]{2.1, 3.4, 5.2, 6};
        double[] yValues = new double[]{-2.4, 1.2, 3, 5.1};
        TabulatedFunction arrayTabulateObject = new ArrayTabulatedFunction(xValues, yValues);
        Assert.assertEquals(arrayTabulateObject.getCount(), 4);
        Assert.assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{1}, new double[]{3}));
    }

    @Test
    public void testGetCount() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.getCount(), 4);
        assertEquals(firstListOfFunction.getCount(), 9);
        assertEquals(secondListOfFunction.getCount(), 19);
        assertEquals(thirdListOfFunction.getCount(), 70);
    }

    @Test
    public void testGetX() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.getX(1), 5.4);
        assertEquals(firstListOfFunction.getX(0), 2.0);
        assertEquals(secondListOfFunction.getX(5), -2.5, DELTA);
        assertEquals(thirdListOfFunction.getX(7), 13.7101, DELTA);
    }

    @Test
    public void testGetY() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.getY(3), 5.1);
        assertEquals(firstListOfFunction.getY(0), 1.0);
        assertEquals(secondListOfFunction.getY(5), -1.25, DELTA);
        assertEquals(thirdListOfFunction.getY(7), 6.855, DELTA);
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.leftBound(), 4.3);
        assertEquals(firstListOfFunction.leftBound(), 2.0);
        assertEquals(secondListOfFunction.leftBound(), -5.0);
        assertEquals(thirdListOfFunction.leftBound(), 13.0);
    }

    @Test
    public void testRightBound() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.rightBound(), 6.4);
        assertEquals(firstListOfFunction.rightBound(), 6.0, DELTA);
        assertEquals(secondListOfFunction.rightBound(), 4.0, DELTA);
        assertEquals(thirdListOfFunction.rightBound(), 20.0, DELTA);
    }

    @Test
    public void testSetY() {
        TabulatedFunction array = createFromArray();
        array.setY(3, 40);

        assertEquals(array.getY(3), 40, DELTA);
    }

    @Test
    public void testIndexOfY() {
        HalfFunction sqObject = new HalfFunction();
        TabulatedFunction arrayTabulateObjectTwo = new ArrayTabulatedFunction(sqObject, 1.2, 67.2, 101);
        Assert.assertEquals(arrayTabulateObjectTwo.indexOfY(0.432), -1, DELTA);
        for (int element = 0; element < 99; element++) {
            Assert.assertEquals(arrayTabulateObjectTwo.indexOfY(sqObject.apply(arrayTabulateObjectTwo.getX(element))), element);
        }
    }

    @Test
    public void testFloorIndexOfX() {
        HalfFunction sqObject = new HalfFunction();
        AbstractTabulatedFunction arrayTabulateObjectThree = new ArrayTabulatedFunction(sqObject, 1, 11, 11);
        for (int element = 0; element < arrayTabulateObjectThree.getCount(); element++) {
            Assert.assertEquals(arrayTabulateObjectThree.floorIndexOfX(1 + element), element);
        }
        Assert.assertEquals(arrayTabulateObjectThree.floorIndexOfX(4.6), 3);
        Assert.assertEquals(arrayTabulateObjectThree.floorIndexOfX(10.2), 9);
        Assert.assertThrows(IllegalArgumentException.class, () -> arrayTabulateObjectThree.floorIndexOfX(-2));
    }

    @Test
    public void testExtrapolateLeft() {
        HalfFunction sqObject = new HalfFunction();
        AbstractTabulatedFunction arrayTabulateObjectTwo = new ArrayTabulatedFunction(sqObject, 1.2, 67.2, 101);
        Assert.assertEquals(arrayTabulateObjectTwo.extrapolateLeft(1.1), 0.55, DELTA);
        Assert.assertEquals(arrayTabulateObjectTwo.extrapolateLeft(0.9), 0.45, DELTA);
        Assert.assertEquals(arrayTabulateObjectTwo.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testExtrapolateRight() {
        HalfFunction sqObject = new HalfFunction();
        AbstractTabulatedFunction arrayTabulateObjectTwo = new ArrayTabulatedFunction(sqObject, 1, 15, 15);
        Assert.assertEquals(arrayTabulateObjectTwo.extrapolateRight(15.5), 7.75, DELTA);
        Assert.assertEquals(arrayTabulateObjectTwo.extrapolateRight(16), 8.0, DELTA);
        Assert.assertEquals(arrayTabulateObjectTwo.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
    }

    @Test
    public void testInterpolate() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.interpolate(4.0, 1), -2.3333333333333366, DELTA);
        assertEquals(firstListOfFunction.interpolate(4.0, 2), 2.0);
        assertEquals(secondListOfFunction.interpolate(0.0, 3), 0.0);
        assertEquals(thirdListOfFunction.interpolate(15.0, 20), 7.5);
    }

    @Test
    public void testApply() {
        double[] xValues = new double[]{2.0, 2.9, 3.6, 4.5, 5.7, 6.3, 7.1};
        double[] yValues = new double[]{5.9, 5.4, 4.9, 4.2, 3.7, 3.1, 2.4};
        MathFunction array = new ArrayTabulatedFunction(xValues, yValues);
        Assert.assertEquals(array.apply(4.5), 4.2); //х найден в таблице
        Assert.assertEquals(array.apply(1.8), 6.01111, DELTA); // х меньше левой границы
        Assert.assertEquals(array.apply(7.5), 2.05, DELTA); // х больше правой границы
        Assert.assertEquals(array.apply(5), 3.99166, DELTA); //х внутри некоторого интервала
    }

    @Test
    public static void testIterator() {
        Iterator<Point> iterator = arrayTabulatedObject.iterator();
        int element = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            Assert.assertEquals(point.x, arrayTabulatedObject.getX(element), DELTA);
            Assert.assertEquals(point.y, arrayTabulatedObject.getY(element++), DELTA);
        }
        Assert.assertThrows(NoSuchElementException.class, iterator::next);
        Assert.assertEquals(element, arrayTabulatedObject.getCount());
        element = 0;
        for (Point point : arrayTabulatedObject) {
            Assert.assertEquals(point.x, arrayTabulatedObject.getX(element), DELTA);
            Assert.assertEquals(point.y, arrayTabulatedObject.getY(element++), DELTA);
        }
        Assert.assertEquals(element, arrayTabulatedObject.getCount());
    }
}