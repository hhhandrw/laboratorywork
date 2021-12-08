package ru.ssau.tk.respect.laboratorywork1.functions;

import ru.ssau.tk.respect.laboratorywork1.exceptions.InterpolationException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.Serializable;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Serializable {
    private static final long serialVersionUID = 476017482196043048L;
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        if (xValues.length < 2 | yValues.length < 2) {
            throw new IllegalArgumentException("xValues.length < 2 or yValues.length < 2");
        }
        checkSorted(xValues);
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, xValues.length);
        count = xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("From >= To");
        }
        if (count < 2) {
            throw new IllegalArgumentException("count < 2");
        }
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
    public int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("x < leftBound()");
        }
        if (x > rightBound()) {
            return count - 1;
        } else if (Arrays.binarySearch(xValues, x) < 0) {
            return Math.abs(Arrays.binarySearch(xValues, x) + 2);
        }
        return Arrays.binarySearch(xValues, x);
    }

    @Override
    public double extrapolateLeft(double x) {
        return super.interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    public double extrapolateRight(double x) {
        return super.interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        return super.interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        yValues[index] = value;
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
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    public void insert(double x, double y) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                yValues[i] = y;
                break;
            } else if (xValues[i] > x) {
                double[] upXValues = new double[count + 1];
                double[] upYValues = new double[count + 1];
                insertIntoArr(xValues, upXValues, i, x);
                insertIntoArr(yValues, upYValues, i, y);
                xValues = upXValues;
                yValues = upYValues;
                count++;
                break;
            }
        }
    }

    private void insertIntoArr(double[] from, double[] in, int index, double value) {
        System.arraycopy(from, 0, in, 0, index);
        in[index] = value;
        System.arraycopy(from, index, in, index + 1, from.length - index);
    }

    public void remove(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("IndexOutOfBounds");
        }
        double[] upXValues = new double[count - 1];
        double[] upYValues = new double[count - 1];
        removeIntoArr(xValues, upXValues, index);
        removeIntoArr(yValues, upYValues, index);
        xValues = upXValues;
        yValues = upYValues;
        count++;
    }

    private void removeIntoArr(double[] from, double[] in, int index) {
        System.arraycopy(from, 0, in, 0, index - 1);
        System.arraycopy(from, index, in, index - 1, from.length - index);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    Point point = new Point(xValues[i], yValues[i]);
                    i++;
                    return point;
                }
                throw new NoSuchElementException();
            }
        };
    }
}