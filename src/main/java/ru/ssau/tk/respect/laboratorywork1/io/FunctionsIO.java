package ru.ssau.tk.respect.laboratorywork1.io;

import ru.ssau.tk.respect.laboratorywork1.functions.Point;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;

import java.io.*;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point point : function) {
            out.writeDouble(point.x);
            out.writeDouble(point.y);
        }
        out.flush();
    }
}
