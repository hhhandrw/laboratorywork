package ru.ssau.tk.respect.laboratorywork1.io;

import ru.ssau.tk.respect.laboratorywork1.functions.ArrayTabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {

    public static void main(String[] args) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream("input/serialized array functions.bin"));
             BufferedInputStream bufferedInputStream = new BufferedInputStream(
                     new FileInputStream("output/serialized array functions.bin"))) {
            TabulatedFunction arrayTabulated = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{6, 7, 8, 9, 10});
            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
            TabulatedFunction firstDerivative = tabulatedDifferentialOperator.derive(arrayTabulated);
            TabulatedFunction secondDerivative = tabulatedDifferentialOperator.derive(firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, arrayTabulated);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);

            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
        } catch (ClassNotFoundException | IOException exception) {
            exception.printStackTrace();
        }
    }
}