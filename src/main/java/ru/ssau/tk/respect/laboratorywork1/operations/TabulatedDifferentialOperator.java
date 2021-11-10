package ru.ssau.tk.respect.laboratorywork1.operations;

import ru.ssau.tk.respect.laboratorywork1.functions.Point;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        int length = points.length;
        double[] xValues = new double[length];
        double[] yValues = new double[length];

        for (int i = 0; i < length - 1; i++) {
            xValues[i] = points[i].x;
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }

        xValues[length - 1] = points[length - 1].x;
        yValues[length - 1] = yValues[length - 2];

        return factory.create(xValues, yValues);
    }
}
