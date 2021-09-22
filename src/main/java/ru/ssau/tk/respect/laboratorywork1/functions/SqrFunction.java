package ru.ssau.tk.respect.laboratorywork1.functions;

public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        x = Math.pow(x, 2);
        return x;
    }
}
