package ru.ssau.tk.respect.laboratorywork1.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private double[] xValues;
    private double[] yValues;
    private int count;

    ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {

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
        return xValues[getCount()];
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x)
                return i;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y)
                return i;
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        for (int i = count; i > 0; i--) {
            if (xValues[i] < x)
                return i;
        }
        return 0;
    }

    @Override
    public double extrapolateLeft(double x) {
        return yValues[0] + (yValues[1] - yValues[0]) / (xValues[1] - xValues[0]) * (x - xValues[0]);
    }

    @Override
    public double extrapolateRight(double x) {
        return yValues[count - 1] + (yValues[count] - yValues[count - 1]) / (xValues[count] - xValues[count - 1]) *
                (x - xValues[count - 1]);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        return yValues[floorIndex - 1] + (yValues[floorIndex] - yValues[floorIndex - 1]) / (xValues[floorIndex] -
                xValues[floorIndex - 1]) * (x - xValues[floorIndex - 1]);
    }
}