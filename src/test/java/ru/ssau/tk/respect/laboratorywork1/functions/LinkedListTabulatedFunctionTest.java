package ru.ssau.tk.respect.laboratorywork1.functions;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedListTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{4, 6, 8, 10, 12};

    private final MathFunction firstFunction = new HalfFunction();
    private final MathFunction secondFunction = new TanFunction();

    private TabulatedFunction createFirstFunction() {
        return new LinkedListTabulatedFunction(firstFunction, 2, 6, 9);
    }

    private TabulatedFunction createSecondFunction() {
        return new LinkedListTabulatedFunction(firstFunction, -5, 4, 19);
    }

    private TabulatedFunction createThirdFunction() {
        return new LinkedListTabulatedFunction(firstFunction, 13, 20, 60);
    }

    private TabulatedFunction createForthFunction() {
        return new ArrayTabulatedFunction(secondFunction, 11, 25, 50);
    }

    private TabulatedFunction createFromArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testConstructorExceptions() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            double[] xValues = new double[]{1};
            double[] yValues = new double[]{4};
            new LinkedListTabulatedFunction(xValues, yValues);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(firstFunction, 2, 6, 1));
        Assert.assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(firstFunction, 6, 2, 9));
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction array = (LinkedListTabulatedFunction) createFromArray();
        array.addNode(10, 111);

        assertEquals(array.rightBound(), 10.0);
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
        assertEquals(thirdListOfFunction.getCount(), 60);
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();
        TabulatedFunction thirdListOfFunction = createThirdFunction();

        assertEquals(firstListOfFunction.leftBound(), 2.0);
        assertEquals(secondListOfFunction.leftBound(), -5.0);
        assertEquals(thirdListOfFunction.leftBound(), 13.0);
        assertEquals(array.leftBound(), 1.0);
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
    public void testGetNode() {
        LinkedListTabulatedFunction array = (LinkedListTabulatedFunction) createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = (LinkedListTabulatedFunction) createFirstFunction();

        assertEquals(array.getNode(2).x, 5.0);
        assertEquals(array.getNode(2).y, 8.0);
        assertEquals(firstListOfFunction.getNode(0).x, 2.0);
        assertEquals(firstListOfFunction.getNode(0).y, 1.0);
        assertEquals(firstListOfFunction.getNode(1).x, 2.5);
        assertEquals(firstListOfFunction.getNode(1).y, 1.25);

        Assert.assertThrows(IllegalArgumentException.class, () -> array.getNode(-2));
        Assert.assertThrows(IllegalArgumentException.class, () -> array.getNode(10));
        Assert.assertThrows(IllegalArgumentException.class, () -> firstListOfFunction.getNode(-3));
        Assert.assertThrows(IllegalArgumentException.class, () -> firstListOfFunction.getNode(9));
        Assert.assertThrows(IllegalArgumentException.class, () -> firstListOfFunction.getNode(15));
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
        assertEquals(thirdListOfFunction.getX(7), 13.8305, DELTA);

        Assert.assertThrows(IllegalArgumentException.class, () -> array.getX(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> array.getX(10));
        Assert.assertThrows(IllegalArgumentException.class, () -> firstListOfFunction.getX(-4));
        Assert.assertThrows(IllegalArgumentException.class, () -> secondListOfFunction.getX(20));
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
        assertEquals(thirdListOfFunction.getY(7), 6.9152, DELTA);

        Assert.assertThrows(IllegalArgumentException.class, () -> array.getY(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> array.getY(10));
        Assert.assertThrows(IllegalArgumentException.class, () -> firstListOfFunction.getY(-4));
        Assert.assertThrows(IllegalArgumentException.class, () -> secondListOfFunction.getY(20));
    }

    @Test
    public void testSetY() {
        TabulatedFunction array = createFromArray();
        TabulatedFunction firstListOfFunction = createFirstFunction();
        TabulatedFunction secondListOfFunction = createSecondFunction();

        array.setY(3, 40);
        assertEquals(array.getY(3), 40, DELTA);

        Assert.assertThrows(IllegalArgumentException.class, () -> array.setY(-1, 5));
        Assert.assertThrows(IllegalArgumentException.class, () -> array.setY(10, 5));
        Assert.assertThrows(IllegalArgumentException.class, () -> firstListOfFunction.setY(-4, 5));
        Assert.assertThrows(IllegalArgumentException.class, () -> secondListOfFunction.setY(20, 5));
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

        assertEquals(array.floorIndexOfX(3.0), 1);
        assertEquals(firstListOfFunction.floorIndexOfX(4.0), 4);
        assertEquals(secondListOfFunction.floorIndexOfX(0.0), 10);

        Assert.assertThrows(IllegalArgumentException.class, () -> array.floorIndexOfX(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> firstListOfFunction.floorIndexOfX(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> secondListOfFunction.floorIndexOfX(-15));
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
        assertEquals(thirdListOfFunction.interpolate(15.0, 2), 7.5);
    }

    @Test
    public void testFloorNodeOfX () {
        LinkedListTabulatedFunction array = (LinkedListTabulatedFunction) createFromArray();
        LinkedListTabulatedFunction firstListOfFunction = (LinkedListTabulatedFunction) createFirstFunction();

        assertEquals(array.floorIndexOfX(3), 1);
        assertEquals(firstListOfFunction.floorIndexOfX(3), 2);

        Assert.assertThrows(IllegalArgumentException.class, () -> array.floorNodeOfX(-1));
        Assert.assertThrows(IllegalArgumentException.class, () -> firstListOfFunction.floorNodeOfX(-1));
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
}