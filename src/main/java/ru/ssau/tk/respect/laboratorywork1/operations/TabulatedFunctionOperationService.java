package ru.ssau.tk.respect.laboratorywork1.operations;

import ru.ssau.tk.respect.laboratorywork1.functions.Point;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point point : tabulatedFunction) {
            points[i] = new Point(tabulatedFunction.getX(i), tabulatedFunction.getY(i));
        }
        return points;
    }

}
