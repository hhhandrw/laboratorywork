package ru.ssau.tk.respect.laboratorywork1.functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY)/(rightX - leftX) * (x - leftX);
    }

    public double apply(double x) {
        if (x < leftBound())
            return extrapolateLeft(x);
        else if (x > rightBound())
            return extrapolateRight(x);
        else {
            if (indexOfX(x) != -1) {
                return getY(indexOfX(x));
            } else {
                return interpolate(x, floorIndexOfX(x));
            }
        }
    }
}