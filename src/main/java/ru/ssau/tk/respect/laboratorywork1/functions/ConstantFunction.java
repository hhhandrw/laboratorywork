package ru.ssau.tk.respect.laboratorywork1.functions;

public class ConstantFunction implements MathFunction {

    private final double CONSTANT;

    public ConstantFunction(double CONSTANT) {
        this.CONSTANT = CONSTANT;
    }

    public double getConstant() {
        return CONSTANT;
    }

    public double apply(double x) {
        return CONSTANT;
    }

}