package ru.ssau.tk.respect.laboratorywork1.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class LinkedListTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{4, 6, 8, 10, 12};

    private final MathFunction firstFunction = new HalfFunction();
    private final MathFunction secondFunction = new TanFunction();

    private LinkedListTabulatedFunction createFirstFunction() {
        return new LinkedListTabulatedFunction(firstFunction, 2, 6, 15);
    }

    private LinkedListTabulatedFunction createSecondFunction() {
        return new LinkedListTabulatedFunction(firstFunction, -5, 4, 30);
    }

    private LinkedListTabulatedFunction createThirdFunction() {
        return new LinkedListTabulatedFunction(firstFunction, 13, 20, 60);
    }

    private ArrayTabulatedFunction createForthFunction() {
        return new ArrayTabulatedFunction(secondFunction, 11, 25, 50);
    }

    private LinkedListTabulatedFunction createFromArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction array = createFromArray();
        array.addNode(10, 111);

        assertEquals(array.rightBound(), 10.0);
    }

    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.getCount(), 5);
        assertEquals(firstListOfFunction.getCount(), 15);
        assertEquals(secondListOfFunction.getCount(), 30);
        assertEquals(thirdListOfFunction.getCount(), 60);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(firstListOfFunction.leftBound(), 2.0);
        assertEquals(secondListOfFunction.leftBound(), -5.0);
        assertEquals(thirdListOfFunction.leftBound(), 13.0);
        assertEquals(array.leftBound(), 1.0);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.rightBound(), 9.0);
        assertEquals(firstListOfFunction.rightBound(), 6.0, DELTA);
        assertEquals(secondListOfFunction.rightBound(), 4.0, DELTA);
        assertEquals(thirdListOfFunction.rightBound(), 20.0, DELTA);
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(firstListOfFunction.getX(0), 2.0);
        assertEquals(secondListOfFunction.getX(5), -3.4482, DELTA);
        assertEquals(thirdListOfFunction.getX(7), 13.8305, DELTA);

        assertEquals(array.getX(1), 3.0);
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.getY(3), 10.0);
        assertEquals(firstListOfFunction.getY(0), 1.0);
        assertEquals(secondListOfFunction.getY(5), -1.7241, DELTA);
        assertEquals(thirdListOfFunction.getY(7), 6.9152, DELTA);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction array = createFromArray();
        array.setY(3, 40);

        assertEquals(array.getY(3), 40, DELTA);
    }

    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.indexOfX(9.0), 4);
        assertEquals(firstListOfFunction.indexOfX(2.0), 0);
        assertEquals(secondListOfFunction.indexOfX(4.00000001), -1);
        assertEquals(thirdListOfFunction.indexOfX(15.0), -1);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.indexOfY(6.0), 1);
        assertEquals(firstListOfFunction.indexOfY(1.0), 0);
        assertEquals(secondListOfFunction.indexOfY(2.00005), -1);
        assertEquals(thirdListOfFunction.indexOfY(15.0), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.floorIndexOfX(4.0), 1);
        assertEquals(firstListOfFunction.floorIndexOfX(4.0), 7);
        assertEquals(secondListOfFunction.floorIndexOfX(0.0), 16);
        assertEquals(thirdListOfFunction.floorIndexOfX(15.0), 16);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.extrapolateLeft(0.0), 3.0);
        assertEquals(firstListOfFunction.extrapolateLeft(-10.0), -5.0);
        assertEquals(secondListOfFunction.extrapolateLeft(-15.0), -7.5);
        assertEquals(thirdListOfFunction.extrapolateLeft(10.0), 5.0);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.extrapolateRight(10.0), 13.0);
        assertEquals(firstListOfFunction.extrapolateRight(7.0), 3.5);
        assertEquals(secondListOfFunction.extrapolateRight(5.0), 2.5);
        assertEquals(thirdListOfFunction.extrapolateRight(30.0), 15.0);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();
        LinkedListTabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(array.interpolate(4.0, 1), 7.0);
        assertEquals(firstListOfFunction.interpolate(4.0, 2), 2.0);
        assertEquals(secondListOfFunction.interpolate(0.0, 3), 0.0);
        assertEquals(thirdListOfFunction.interpolate(15.0, 2), 7.5);
    }

    @Test
    public void testCompositeFunction() {
        MathFunction firstListOfFunction = createFirstFunction();
        MathFunction secondListOfFunction = createSecondFunction();
        MathFunction thirdListOfFunction = createThirdFunction();
        MathFunction forthListOfFunction = createForthFunction();

        assertEquals(firstListOfFunction.andThen(secondListOfFunction).apply(2), 0.5, DELTA);
        assertEquals(secondListOfFunction.andThen(firstListOfFunction).apply(6), 1.5, DELTA);
        assertEquals(firstListOfFunction.andThen(firstFunction).apply(3), 0.75, DELTA);
        assertEquals(firstFunction.andThen(secondListOfFunction).apply(5), 1.25, DELTA);
        assertEquals(firstFunction.andThen(firstListOfFunction).apply(7), 1.75, DELTA);
        assertEquals(thirdListOfFunction.andThen(forthListOfFunction).apply(45), 0.5713, DELTA);
        assertEquals(thirdListOfFunction.andThen(forthListOfFunction).andThen(secondFunction).apply(105), 38.2382, DELTA);
        assertEquals(thirdListOfFunction.andThen(forthListOfFunction).andThen(firstFunction).apply(50), -0.0667, DELTA);
        assertEquals(forthListOfFunction.andThen(thirdListOfFunction).andThen(firstFunction).apply(55), 8.1354, DELTA);
    }

    @Test
    public void testApply() {
        LinkedListTabulatedFunction array = createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = createFirstFunction();
        LinkedListTabulatedFunction secondListOfFunction = createSecondFunction();

        assertEquals(firstListOfFunction.apply(1), 0.5, DELTA);
        assertEquals(secondListOfFunction.apply(-9), -4.5, DELTA);
        assertEquals(array.apply(5), 8.0, DELTA);
    }

}