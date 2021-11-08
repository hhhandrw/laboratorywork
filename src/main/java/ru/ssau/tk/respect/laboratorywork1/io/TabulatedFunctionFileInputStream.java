package ru.ssau.tk.respect.laboratorywork1.io;

import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {

    public static void main(String[] args) {
        File binaryFunction = new File("input/binary function.bin");

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(binaryFunction))) {
            TabulatedFunction arrayTabulatedFunction = FunctionsIO.readTabulatedFunction(in, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayTabulatedFunction.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(inputStreamReader);
            System.out.println("Введите размер и значения функции");
            TabulatedFunction linkedListTabulatedFunction = FunctionsIO.readTabulatedFunction(in, new LinkedListTabulatedFunctionFactory());
            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            TabulatedFunction derivation = differentialOperator.derive(linkedListTabulatedFunction);
            System.out.println(derivation.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
