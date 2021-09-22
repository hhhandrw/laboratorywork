package ru.ssau.tk.respect.laboratorywork1.functions;

public class ConstantFunction implements MathFunction {

    private final double constant;

    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    public double getConstant() {
        return constant;
    }

    public double apply(double x) {
        return constant;
    }
}