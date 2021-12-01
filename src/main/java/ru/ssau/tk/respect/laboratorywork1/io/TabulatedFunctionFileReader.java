package ru.ssau.tk.respect.laboratorywork1.io;

import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.TabulatedFunctionFactory;

import java.io.*;

public class TabulatedFunctionFileReader {

    public static void main(String[] args) {
        try (BufferedReader bufferedReaderToArray = new BufferedReader(
                new FileReader("input/function.txt")); BufferedReader bufferedReaderToLinkedList = new BufferedReader(
                new FileReader("input/function.txt"))) {
            TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
            TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
            System.out.println(FunctionsIO.readTabulatedFunction(bufferedReaderToArray, arrayFactory).toString());
            System.out.println(FunctionsIO.readTabulatedFunction(bufferedReaderToLinkedList, linkedListFactory).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}