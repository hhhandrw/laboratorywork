package ru.ssau.tk.respect.laboratorywork1.operations;

import ru.ssau.tk.respect.laboratorywork1.functions.Point;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            points[i++] = point;
        }
        return points;
    }
}
