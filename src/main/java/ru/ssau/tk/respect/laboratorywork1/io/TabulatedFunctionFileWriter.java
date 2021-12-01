package ru.ssau.tk.respect.laboratorywork1.io;

import ru.ssau.tk.respect.laboratorywork1.functions.ArrayTabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileWriter {

    public static void main(String[] args) {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 2, 3, 4, 5};
        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkedFunction = new LinkedListTabulatedFunction(xValues, yValues);
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("output/test character streams.txt"))) {
            FunctionsIO.writeTabulatedFunction(bufferedWriter, arrayFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(
                             new FileWriter("output/linked list function.txt"))) {
            FunctionsIO.writeTabulatedFunction(bufferedWriter, linkedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
