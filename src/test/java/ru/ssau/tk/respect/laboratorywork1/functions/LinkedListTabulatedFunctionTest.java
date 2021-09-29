package ru.ssau.tk.respect.laboratorywork1.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class LinkedListTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{4, 6, 8, 10, 12};

    private final MathFunction function = new TanFunction();

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
        assertEquals(secondListOfFunction.getX(0), -5.0);
        assertEquals(thirdListOfFunction.getX(0), 13.0);

        assertEquals(array.getX(0), 1.0);
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();
        LinkedListTabulatedFunction thirdListOfFunction = thirdGetFromFunction();

        assertEquals(firstListOfFunction.getY(0), -2.185, DELTA);
        assertEquals(secondListOfFunction.getY(0), 3.3805, DELTA);
        assertEquals(thirdListOfFunction.getY(0), 0.4630, DELTA);
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

        assertEquals(array.indexOfX(7), 3);
        assertEquals(firstListOfFunction.indexOfX(3), -1);
        assertEquals(firstListOfFunction.indexOfX(100), -1);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction array = getArray();

        assertEquals(array.indexOfY(8), 2);
        assertEquals(array.indexOfY(10), 3);
        assertEquals(array.indexOfY(12), 4);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();

        assertEquals(array.floorIndexOfX(5.5), 2);
        assertEquals(array.floorIndexOfX(1.2), 0);
        assertEquals(firstListOfFunction.floorIndexOfX(1.1), 0);
        assertEquals(firstListOfFunction.floorIndexOfX(100), 15);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();
        LinkedListTabulatedFunction secondListOfFunction = secondGetFromFunction();

        assertEquals(secondListOfFunction.extrapolateLeft(-4), -149.2248, DELTA);
        assertEquals(firstListOfFunction.extrapolateLeft(0), -9.4168, DELTA);
        assertEquals(array.extrapolateLeft(0), 3.0, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction array = getArray();
        LinkedListTabulatedFunction firstListOfFunction = firstGetFromFunction();

        assertEquals(firstListOfFunction.extrapolateRight(2), -5.1687, DELTA);
        assertEquals(firstListOfFunction.extrapolateRight(10), 4.5867, DELTA);
        assertEquals(array.extrapolateRight(4), 7, DELTA);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction array = getArray();

        assertEquals(array.interpolate(7, 3), 10.0);
        assertEquals(array.interpolate(3, 1), 6.0);
        assertEquals(array.interpolate(5, 2), 8.0);
    }

}