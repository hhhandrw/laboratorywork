package ru.ssau.tk.respect.laboratorywork1.io;

import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;

public class TabulatedFunctionFileReader {

    public static void main(String[] args) {
        try (BufferedReader arrayReader = new BufferedReader(
                new FileReader("input/function.txt"));
             BufferedReader listReader = new BufferedReader(
                     new FileReader("input/function.txt"))) {
            TabulatedFunction arrayTabulated = FunctionsIO.readTabulatedFunction(arrayReader, new ArrayTabulatedFunctionFactory());
            TabulatedFunction listTabulated = FunctionsIO.readTabulatedFunction(listReader, new LinkedListTabulatedFunctionFactory());
            System.out.println(arrayTabulated);
            System.out.println(listTabulated);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}