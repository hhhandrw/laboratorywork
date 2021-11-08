package ru.ssau.tk.respect.laboratorywork1.operations;

import ru.ssau.tk.respect.laboratorywork1.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.respect.laboratorywork1.functions.Point;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            points[i] = point;
            i++;
        }
        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException("Functions have different number of points");
        }

        Point[] firstPoints = asPoints(a);
        Point[] secondPoints = asPoints(b);

        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[a.getCount()];

        for (int i = 0; i < a.getCount(); i++) {
            if (firstPoints[i].x != secondPoints[i].x) {
                throw new InconsistentFunctionsException("Functions have different meanings of X");
            }
            xValues[i] = firstPoints[i].x;
            yValues[i] = operation.apply(firstPoints[i].y, secondPoints[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction summarize(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction divide(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }
}
