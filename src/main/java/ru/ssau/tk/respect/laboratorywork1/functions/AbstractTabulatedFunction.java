package ru.ssau.tk.respect.laboratorywork1.functions;

import ru.ssau.tk.respect.laboratorywork1.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.respect.laboratorywork1.exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected int count;

    public abstract int floorIndexOfX(double x);

    public abstract double extrapolateLeft(double x);

    public abstract double extrapolateRight(double x);

    public abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues) throws DifferentLengthOfArraysException {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException();
        }
    }

    protected static void checkSorted(double[] xValues) throws ArrayIsNotSortedException {
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i - 1]) {
                throw new ArrayIsNotSortedException();
            }
        }
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else {
            if (indexOfX(x) != -1) {
                return getY(indexOfX(x));
            } else {
                return interpolate(x, floorIndexOfX(x));
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName()).append(" size = ").append(this.getCount()).append("\n");

        for (Point point : this) {
            stringBuilder.append("[").append(point.x).append("; ").append(point.y).append("]\n");
        }

        return stringBuilder.toString();
    }
}

