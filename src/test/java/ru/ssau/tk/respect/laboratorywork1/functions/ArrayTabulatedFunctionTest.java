package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.respect.laboratorywork1.exceptions.DifferentLengthOfArraysException;

import java.util.Iterator;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{4, 6, 8, 10, 12};

    private final MathFunction function = new HalfFunction();

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
        Assert.assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] xValues = new double[]{1, 3, 5, 7};
            double[] yValues = new double[]{4, 6, 8};
            new ArrayTabulatedFunction(xValues, yValues);
        });
        Assert.assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] xValues = new double[]{1, 3, 10, 5, 7};
            double[] yValues = new double[]{4, 6, 8, 10, 12};
            new ArrayTabulatedFunction(xValues, yValues);
        });
    }

    @Test
    public void testGetCount() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.getCount(), 5);
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

        assertEquals(array.getX(1), 3.0);
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

        assertEquals(array.getY(3), 10.0);
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

        assertEquals(array.leftBound(), 1.0);
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

        assertEquals(array.rightBound(), 9.0);
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
    public void testIndexOfX() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.indexOfX(9.0), 4);
        assertEquals(firstListOfFunction.indexOfX(2.0), 0);
        assertEquals(secondListOfFunction.indexOfX(4.00000001), -1);
        assertEquals(thirdListOfFunction.indexOfX(15.0), -1);
    }

    @Test
    public void testIndexOfY() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.indexOfY(6.0), 1);
        assertEquals(firstListOfFunction.indexOfY(1.0), 0);
        assertEquals(secondListOfFunction.indexOfY(2.00005), -1);
        assertEquals(thirdListOfFunction.indexOfY(15.0), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();

        assertEquals(array.floorIndexOfX(4.0), 1);
        assertEquals(firstListOfFunction.floorIndexOfX(4.0), 4);
        assertEquals(secondListOfFunction.floorIndexOfX(0.0), 10);
    }

    @Test
    public void testExtrapolateLeft() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.extrapolateLeft(0.0), 3.0);
        assertEquals(firstListOfFunction.extrapolateLeft(-10.0), -5.0);
        assertEquals(secondListOfFunction.extrapolateLeft(-15.0), -7.5);
        assertEquals(thirdListOfFunction.extrapolateLeft(10.0), 5.0);
    }

    @Test
    public void testExtrapolateRight() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.extrapolateRight(10.0), 13.0);
        assertEquals(firstListOfFunction.extrapolateRight(7.0), 3.5);
        assertEquals(secondListOfFunction.extrapolateRight(5.0), 2.5);
        assertEquals(thirdListOfFunction.extrapolateRight(30.0), 15.0);
    }

    @Test
    public void testInterpolate() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.interpolate(4.0, 1), 7.0);
        assertEquals(firstListOfFunction.interpolate(4.0, 2), 2.0);
        assertEquals(secondListOfFunction.interpolate(0.0, 3), 0.0);
        assertEquals(thirdListOfFunction.interpolate(15.0, 20), 7.5);
    }

    @Test
    public void testApply() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();

        assertEquals(array.apply(-5), -2, DELTA);
        assertEquals(array.apply(15), 18, DELTA);
        assertEquals(array.apply(5), 8, DELTA);
        assertEquals(array.apply(1.4), 4.4, DELTA);

        assertEquals(firstListOfFunction.apply(-4), -2.0, DELTA);
        assertEquals(firstListOfFunction.apply(16), 8.0, DELTA);
        assertEquals(firstListOfFunction.apply(3), 1.5, DELTA);
        assertEquals(firstListOfFunction.apply(5.3), 2.65, DELTA);

        assertEquals(secondListOfFunction.apply(-10), -5, DELTA);
        assertEquals(secondListOfFunction.apply(8), 4, DELTA);
        assertEquals(secondListOfFunction.apply(-2), -1, DELTA);
        assertEquals(secondListOfFunction.apply(-2.1), -1.05, DELTA);
    }

    @Test
    public void testIterator() {
        TabulatedFunction firstFunction = createFromArray();
        Iterator<Point> firstIterator = firstFunction.iterator();

        TabulatedFunction secondFunction = createFirstFunction();
        Iterator<Point> secondIterator = secondFunction.iterator();

        int a = 0;
        int b = 0;
        while (firstIterator.hasNext()) {
            Point point = firstIterator.next();
            assertEquals(point.x, firstFunction.getX(a++));
            assertEquals(point.y, firstFunction.getY(b++));
        }
        assertEquals(a, 5);
        assertEquals(b, 5);

        int c = 0;
        int d = 0;
        while (secondIterator.hasNext()) {
            Point point = secondIterator.next();
            assertEquals(point.x, secondFunction.getX(c++));
            assertEquals(point.y, secondFunction.getY(d++));
        }
        assertEquals(c, 9);
        assertEquals(d, 9);

        int e = 0;
        int f = 0;
        for (Point point : firstFunction) {
            assertEquals(point.x, firstFunction.getX(e++));
            assertEquals(point.y, firstFunction.getY(f++));
        }
        assertEquals(e, 5);
        assertEquals(f, 5);

        int g = 0;
        int h = 0;
        for (Point point : secondFunction) {
            assertEquals(point.x, secondFunction.getX(g++));
            assertEquals(point.y, secondFunction.getY(h++));
        }
        assertEquals(g, 9);
        assertEquals(h, 9);
    }
}