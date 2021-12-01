package ru.ssau.tk.respect.laboratorywork1.io;

import ru.ssau.tk.respect.laboratorywork1.functions.ArrayTabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {

    public static void main(String[] args) {
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))){
            double[] xValues = {1, 2, 3, 4, 5};
            double[] yValues = {1, 4, 9, 16, 25};
            ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);
            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator();
            ArrayTabulatedFunction firstDifferential = (ArrayTabulatedFunction) tabulatedDifferentialOperator.derive(arrayFunction);
            ArrayTabulatedFunction secondDifferential = (ArrayTabulatedFunction) tabulatedDifferentialOperator.derive(firstDifferential);
            FunctionsIO.serialize(bufferedOutputStream, arrayFunction);
            FunctionsIO.serialize(bufferedOutputStream, firstDifferential);
            FunctionsIO.serialize(bufferedOutputStream, secondDifferential);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("input/serialized array functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
            System.out.println(FunctionsIO.deserialize(bufferedInputStream).toString());
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
