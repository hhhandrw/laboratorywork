package ru.ssau.tk.respect.laboratorywork1.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final static double DELTA = 0.0001;
    private int count = 5;
    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{4, 6, 8, 10, 12};
    private final MathFunction firstFunction = new HalfFunction();

    private ArrayTabulatedFunction firstGetFromFunction() {
        return new ArrayTabulatedFunction(firstFunction, 2, 6, 15);
    }

    private ArrayTabulatedFunction secondGetFromFunction() {
        return new ArrayTabulatedFunction(firstFunction, -5, 4, 30);
    }

    private ArrayTabulatedFunction thirdGetFromFunction() {
        return new ArrayTabulatedFunction(firstFunction, 13, 20, 60);
    }

    private ArrayTabulatedFunction createFromArray() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testGetCount() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.getCount(), 5);
        assertEquals(firstListOfFunction.getCount(), 15);
        assertEquals(secondListOfFunction.getCount(), 30);
        assertEquals(thirdListOfFunction.getCount(), 60);
    }

    @Test
    public void testGetX() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.getX(1), 3.0);
        assertEquals(firstListOfFunction.getX(0), 2.0);
        assertEquals(secondListOfFunction.getX(5), -3.4482, DELTA);
        assertEquals(thirdListOfFunction.getX(7), 13.8305, DELTA);
    }

    @Test
    public void testGetY() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.getY(3), 10.0);
        assertEquals(firstListOfFunction.getY(0), 1.0);
        assertEquals(secondListOfFunction.getY(5), -1.7241, DELTA);
        assertEquals(thirdListOfFunction.getY(7), 6.9152, DELTA);
    }


    @Test
    public void testLeftBound() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.leftBound(), 1.0);
        assertEquals(firstListOfFunction.leftBound(), 2.0);
        assertEquals(secondListOfFunction.leftBound(), -5.0);
        assertEquals(thirdListOfFunction.leftBound(), 13.0);
    }

    @Test
    public void testRightBound() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.rightBound(), 9.0);
        assertEquals(firstListOfFunction.rightBound(), 5.9999, DELTA);
        assertEquals(secondListOfFunction.rightBound(), 4.0, DELTA);
        assertEquals(thirdListOfFunction.rightBound(), 20.0, DELTA);
    }

    @Test
    public void testIndexOfX() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.indexOfX(9.0), 4);
        assertEquals(firstListOfFunction.indexOfX(2.0), 0);
        assertEquals(secondListOfFunction.indexOfX(4.000000000000001), 29);
        assertEquals(thirdListOfFunction.indexOfX(15.0), -1);
    }

    @Test
    public void testIndexOfY() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.indexOfY(6.0), 1);
        assertEquals(firstListOfFunction.indexOfY(1.0), 0);
        assertEquals(secondListOfFunction.indexOfY(2.0000000000000005), 29);
        assertEquals(thirdListOfFunction.indexOfY(15.0), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.floorIndexOfX(4.0), 1);
        assertEquals(firstListOfFunction.floorIndexOfX(4.0), 7);
        assertEquals(secondListOfFunction.floorIndexOfX(0.0), 16);
        assertEquals(thirdListOfFunction.floorIndexOfX(15.0), 16);
    }

    @Test
    public void testExtrapolateLeft() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.extrapolateLeft(0.0), 3.0);
        assertEquals(firstListOfFunction.extrapolateLeft(-10.0), -5.0);
        assertEquals(secondListOfFunction.extrapolateLeft(-15.0), -7.5);
        assertEquals(thirdListOfFunction.extrapolateLeft(10.0), 5.0);
    }

    @Test
    public void testExtrapolateRight() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.extrapolateRight(10.0), 13.0);
        assertEquals(firstListOfFunction.extrapolateRight(7.0), 3.5);
        assertEquals(secondListOfFunction.extrapolateRight(5.0), 2.5);
        assertEquals(thirdListOfFunction.extrapolateRight(30.0), 15.0);
    }

    @Test
    public void testInterpolate() {
        ArrayTabulatedFunction array = createFromArray();
        ArrayTabulatedFunction firstListOfFunction = firstGetFromFunction();
        ArrayTabulatedFunction secondListOfFunction = secondGetFromFunction();
        ArrayTabulatedFunction thirdListOfFunction = thirdGetFromFunction();
        assertEquals(array.interpolate(4.0, 1), 7.0);
        assertEquals(firstListOfFunction.interpolate(4.0, 2), 2.0);
        assertEquals(secondListOfFunction.interpolate(0.0, 3), 0.0);
        assertEquals(thirdListOfFunction.interpolate(15.0, 2), 7.5);
    }
}