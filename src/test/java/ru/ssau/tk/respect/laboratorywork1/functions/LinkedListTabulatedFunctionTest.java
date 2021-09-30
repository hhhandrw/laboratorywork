package ru.ssau.tk.respect.laboratorywork1.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class LinkedListTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{4, 6, 8, 10, 12};

    private final MathFunction function = new HalfFunction();

    private LinkedListTabulatedFunction firstGetFromFunction() {
        return new LinkedListTabulatedFunction(function, 2, 6, 15);
    }

    private LinkedListTabulatedFunction secondGetFromFunction() {
        return new LinkedListTabulatedFunction(function, -5, 4, 30);
    }

    private LinkedListTabulatedFunction thirdGetFromFunction() {
        return new LinkedListTabulatedFunction(function, 13, 20, 60);
    }

    private LinkedListTabulatedFunction getArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction array = getArray();
        array.addNode(10, 111);

        assertEquals(array.rightBound(), 10.0);
    }

    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(array.getCount(), 5);
        assertEquals(firstListOfFunction.getCount(), 15);
        assertEquals(secondListOfFunction.getCount(), 30);
        assertEquals(thirdListOfFunction.getCount(), 60);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(firstListOfFunction.leftBound(), 2.0);
        assertEquals(secondListOfFunction.leftBound(), -5.0);
        assertEquals(thirdListOfFunction.leftBound(), 13.0);
        assertEquals(array.leftBound(), 1.0);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(array.rightBound(), 9.0);
        assertEquals(firstListOfFunction.rightBound(), 6.0, DELTA);
        assertEquals(secondListOfFunction.rightBound(), 4.0, DELTA);
        assertEquals(thirdListOfFunction.rightBound(), 20.0, DELTA);
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(firstListOfFunction.getX(0), 2.0);
        assertEquals(secondListOfFunction.getX(5), -3.4482, DELTA);
        assertEquals(thirdListOfFunction.getX(7), 13.8305, DELTA);

        assertEquals(array.getX(1), 3.0);
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(array.getY(3), 10.0);
        assertEquals(firstListOfFunction.getY(0), 1.0);
        assertEquals(secondListOfFunction.getY(5), -1.7241, DELTA);
        assertEquals(thirdListOfFunction.getY(7), 6.9152, DELTA);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction array = getArray();
        array.setY(3, 40);

        assertEquals(array.getY(3), 40, DELTA);
    }

    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(array.indexOfX(9.0), 4);
        assertEquals(firstListOfFunction.indexOfX(2.0), 0);
        assertEquals(secondListOfFunction.indexOfX(4.00000001), -1);
        assertEquals(thirdListOfFunction.indexOfX(15.0), -1);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(array.indexOfY(6.0), 1);
        assertEquals(firstListOfFunction.indexOfY(1.0), 0);
        assertEquals(secondListOfFunction.indexOfY(2.00005), -1);
        assertEquals(thirdListOfFunction.indexOfY(15.0), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(array.floorIndexOfX(4.0), 1);
        assertEquals(firstListOfFunction.floorIndexOfX(4.0), 7);
        assertEquals(secondListOfFunction.floorIndexOfX(0.0), 16);
        assertEquals(thirdListOfFunction.floorIndexOfX(15.0), 16);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(array.extrapolateLeft(0.0), 3.0);
        assertEquals(firstListOfFunction.extrapolateLeft(-10.0), -5.0);
        assertEquals(secondListOfFunction.extrapolateLeft(-15.0), -7.5);
        assertEquals(thirdListOfFunction.extrapolateLeft(10.0), 5.0);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(array.extrapolateRight(10.0), 13.0);
        assertEquals(firstListOfFunction.extrapolateRight(7.0), 3.5);
        assertEquals(secondListOfFunction.extrapolateRight(5.0), 2.5);
        assertEquals(thirdListOfFunction.extrapolateRight(30.0), 15.0);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(array.interpolate(4.0, 1), 7.0);
        assertEquals(firstListOfFunction.interpolate(4.0, 2), 2.0);
        assertEquals(secondListOfFunction.interpolate(0.0, 3), 0.0);
        assertEquals(thirdListOfFunction.interpolate(15.0, 2), 7.5);
    }

    @Test
    public void testCompositeFunction() {
        MathFunction firstListOfFunction = firstGetFromFunction();
        MathFunction secondListOfFunction = secondGetFromFunction();

        assertEquals(firstListOfFunction.andThen(secondListOfFunction).apply(2), 1.4672, DELTA);
        assertEquals(secondListOfFunction.andThen(firstListOfFunction).apply(6), 1.5009, DELTA);
        assertEquals(firstListOfFunction.andThen(function).apply(3), -0.1465, DELTA);
        assertEquals(function.andThen(secondListOfFunction).apply(5), -0.2467, DELTA);
        assertEquals(function.andThen(firstListOfFunction).apply(7), -6.2657, DELTA);
    }

    @Test
    public void testApply() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();

        assertEquals(firstListOfFunction.apply(1), 0.5, DELTA);
        assertEquals(secondListOfFunction.apply(-9), -4.5, DELTA);
        assertEquals(array.apply(5), 8.0, DELTA);
    }

}