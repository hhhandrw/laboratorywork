package ru.ssau.tk.respect.laboratorywork1.functions;

import ru.ssau.tk.respect.laboratorywork1.exceptions.InterpolationException;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;
    private final int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
        checkSorted(xValues);
        checkLengthIsTheSame(xValues, yValues);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        xValues = new double[count];
        yValues = new double[count];
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
        return interpolate(x, xValues[0], xValues[1],
                yValues[0], yValues[1]);
    }

    @Override
    public double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, xValues[count - 2], xValues[count - 1],
                yValues[count - 2], yValues[count - 1]);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return yValues[0];
        }
        if (xValues[floorIndex] < leftBound() || xValues[floorIndex] > rightBound() || xValues[floorIndex + 1] > rightBound()) {
            throw new InterpolationException();
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1],
                yValues[floorIndex], yValues[floorIndex + 1]);
    }
}