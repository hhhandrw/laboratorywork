package ru.ssau.tk.respect.laboratorywork1.functions;

public class ConstantFunction implements MathFunction {
    private final double constant;

    public ConstantFunction(double CONSTANT) {
        this.constant = CONSTANT;
    }

    public double getConstant() {
        return constant;
    }

    public double apply(double x) {
        return constant;
    }
}