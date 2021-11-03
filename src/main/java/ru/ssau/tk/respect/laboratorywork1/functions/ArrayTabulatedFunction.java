package ru.ssau.tk.respect.laboratorywork1.functions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;
    private final int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        //TODO: добавить проверку xFrom < xTo и count > 2
        xValues = new double[count];
        yValues = new double[count];
        if (count < 2) {
            throw new IllegalArgumentException("Length is less than permissible");
        }
        if (xFrom < xTo) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        double step = (xTo - xFrom) / (count - 1);
        double j = xFrom;
        for (int i = 0; i < count; i++) {
            yValues[i] = source.apply(j);
            xValues[i] = j;
            j += step;
        }
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        int returned = Arrays.binarySearch(xValues, x);
        return returned < 0 ? -1 : returned;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x > rightBound()) {
            return count - 1;
        } else if (x < leftBound()) {
            return 0;
        } else if (Arrays.binarySearch(xValues, x) < 0) {
            return Math.abs(Arrays.binarySearch(xValues, x) + 2);
        }
        return Arrays.binarySearch(xValues, x);
    }

    @Override
    public double extrapolateLeft(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, 0);
    }

    @Override
    public double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, count - 2);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1],
                yValues[floorIndex], yValues[floorIndex + 1]);
    }

    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }
}