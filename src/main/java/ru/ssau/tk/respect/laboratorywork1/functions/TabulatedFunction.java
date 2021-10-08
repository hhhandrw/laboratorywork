package ru.ssau.tk.respect.laboratorywork1.functions;

public interface TabulatedFunction extends MathFunction {

    int getCount();

    double getX(int index);

    double getY(int index);

    void setY(int index, double value);

    int indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();

    int floorIndexOfX(double x);

    double extrapolateLeft(double x);

    double extrapolateRight(double x);

    double interpolate(double x, int floorIndex);
}
