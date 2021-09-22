package ru.ssau.tk.respect.laboratorywork1.functions;

public class TanFunction implements MathFunction {

    @Override
    public double apply(double x) {
        x = Math.tan(x);
        return x;
    }
}