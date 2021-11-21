package ru.ssau.tk.respect.laboratorywork1.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.functions.*;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {

    private final Object object = new Object();

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{4, 6, 8, 10, 12};
    private final MathFunction function = new HalfFunction();

    private final SynchronizedTabulatedFunction firstSynchronizedTabulatedFunction = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), object);

    private final SynchronizedTabulatedFunction secondSynchronizedTabulatedFunction = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), object);

    private final SynchronizedTabulatedFunction thirdSynchronizedTabulatedFunction = new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(function, -5, 4, 19), object);

    private final SynchronizedTabulatedFunction forthSynchronizedTabulatedFunction = new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(function, -5, 4, 19), object);

    @Test
    public void testGetCount() {
        assertEquals(firstSynchronizedTabulatedFunction.getCount(), 5);
        assertEquals(secondSynchronizedTabulatedFunction.getCount(), 5);
        assertEquals(thirdSynchronizedTabulatedFunction.getCount(), 19);
        assertEquals(forthSynchronizedTabulatedFunction.getCount(), 19);
    }

    @Test
    public void testGetX() {
        assertEquals(firstSynchronizedTabulatedFunction.getX(2), 5.0);
        assertEquals(secondSynchronizedTabulatedFunction.getX(3), 7.0);
        assertEquals(thirdSynchronizedTabulatedFunction.getX(3), -3.5);
        assertEquals(forthSynchronizedTabulatedFunction.getX(6), -2.0);
    }

    @Test
    public void testGetY() {
        assertEquals(firstSynchronizedTabulatedFunction.getY(2), 8.0);
        assertEquals(secondSynchronizedTabulatedFunction.getY(3), 10.0);
        assertEquals(thirdSynchronizedTabulatedFunction.getY(4), -1.5);
        assertEquals(forthSynchronizedTabulatedFunction.getY(6), -1.0);
    }

    @Test
    public void testSetY() {
        firstSynchronizedTabulatedFunction.setY(2, 1.0);
        secondSynchronizedTabulatedFunction.setY(3, 2.0);
        thirdSynchronizedTabulatedFunction.setY(4, 3.0);
        forthSynchronizedTabulatedFunction.setY(6, 4.0);

        assertEquals(firstSynchronizedTabulatedFunction.getY(2), 1.0);
        assertEquals(secondSynchronizedTabulatedFunction.getY(3), 2.0);
        assertEquals(thirdSynchronizedTabulatedFunction.getY(4), 3.0);
        assertEquals(forthSynchronizedTabulatedFunction.getY(6), 4.0);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(firstSynchronizedTabulatedFunction.indexOfX(5.0), 2);
        assertEquals(secondSynchronizedTabulatedFunction.indexOfX(7.0), 3);
        assertEquals(thirdSynchronizedTabulatedFunction.indexOfX(-3.5), 3);
        assertEquals(forthSynchronizedTabulatedFunction.indexOfX(-2.0), 6);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(firstSynchronizedTabulatedFunction.indexOfY(8.0), 2);
        assertEquals(secondSynchronizedTabulatedFunction.indexOfY(10.0), 3);
        assertEquals(thirdSynchronizedTabulatedFunction.indexOfY(-1.5), 4);
        assertEquals(forthSynchronizedTabulatedFunction.indexOfY(-1.0), 6);
    }

    @Test
    public void testLeftBound() {
        assertEquals(firstSynchronizedTabulatedFunction.leftBound(), 1.0);
        assertEquals(secondSynchronizedTabulatedFunction.leftBound(), 1.0);
        assertEquals(thirdSynchronizedTabulatedFunction.leftBound(), -5.0);
        assertEquals(forthSynchronizedTabulatedFunction.leftBound(), -5.0);
    }

    @Test
    public void testRightBound() {
        assertEquals(firstSynchronizedTabulatedFunction.rightBound(), 9.0);
        assertEquals(secondSynchronizedTabulatedFunction.rightBound(), 9.0);
        assertEquals(thirdSynchronizedTabulatedFunction.rightBound(), 4.0);
        assertEquals(forthSynchronizedTabulatedFunction.rightBound(), 4.0);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(firstSynchronizedTabulatedFunction.floorIndexOfX(3.3), 2);
        assertEquals(secondSynchronizedTabulatedFunction.floorIndexOfX(5.3), 3);
        assertEquals(thirdSynchronizedTabulatedFunction.floorIndexOfX(-4.8), 0);
        assertEquals(forthSynchronizedTabulatedFunction.floorIndexOfX(-4.3), 1);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(firstSynchronizedTabulatedFunction.extrapolateLeft(0.0), 3.0);
        assertEquals(secondSynchronizedTabulatedFunction.extrapolateLeft(0.0), 3.0);
        assertEquals(thirdSynchronizedTabulatedFunction.extrapolateLeft(-6), -3.0);
        assertEquals(forthSynchronizedTabulatedFunction.extrapolateLeft(-5.5), -2.75);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(firstSynchronizedTabulatedFunction.extrapolateRight(9.2), 12.2);
        assertEquals(secondSynchronizedTabulatedFunction.extrapolateRight(9.2), 12.2);
        assertEquals(thirdSynchronizedTabulatedFunction.extrapolateRight(5.0), 2.5);
        assertEquals(forthSynchronizedTabulatedFunction.extrapolateRight(30.0), 15.0);
    }

    @Test
    public void testInterpolate() {
        assertEquals(firstSynchronizedTabulatedFunction.interpolate(3.5, 1), 6.5);
        assertEquals(secondSynchronizedTabulatedFunction.interpolate(5.3, 1), 8.3);
        assertEquals(thirdSynchronizedTabulatedFunction.interpolate(-4.3, 1), -2.15);
        assertEquals(forthSynchronizedTabulatedFunction.interpolate(-3.3, 3), -1.65);
    }

    @Test
    public void testApply() {
        assertEquals(firstSynchronizedTabulatedFunction.apply(-10.0), -7.0);
        assertEquals(secondSynchronizedTabulatedFunction.apply(60.0), 63.0);
        assertEquals(thirdSynchronizedTabulatedFunction.apply(20.0), 10.0);
        assertEquals(forthSynchronizedTabulatedFunction.apply(1.4), 0.7);
    }
}