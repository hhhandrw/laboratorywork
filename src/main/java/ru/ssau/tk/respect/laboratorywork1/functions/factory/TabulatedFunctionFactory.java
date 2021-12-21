package ru.ssau.tk.respect.laboratorywork1.functions.factory;

import ru.ssau.tk.respect.laboratorywork1.functions.MathFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);
    TabulatedFunction createFromFunction(MathFunction source, double xFrom, double xTo, int count);
}
