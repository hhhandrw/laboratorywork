package ru.ssau.tk.respect.laboratorywork1.io;

import ru.ssau.tk.respect.laboratorywork1.functions.ArrayTabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.LinkedListTabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        File arrayFunction = new File("output/array function.bin");
        File linkedListFunction = new File("output/linked list function.bin");

        try (BufferedOutputStream arrayFunctionOut = new BufferedOutputStream(new FileOutputStream(arrayFunction));
             BufferedOutputStream listFunctionOut = new BufferedOutputStream(new FileOutputStream(linkedListFunction))) {
            double[] xValues = new double[]{1, 3, 5, 7, 9};
            double[] yValues = new double[]{4, 6, 8, 10, 12};

            ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
            LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(xValues, yValues);

            FunctionsIO.writeTabulatedFunction(arrayFunctionOut, arrayTabulatedFunction);
            FunctionsIO.writeTabulatedFunction(listFunctionOut, linkedListTabulatedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
