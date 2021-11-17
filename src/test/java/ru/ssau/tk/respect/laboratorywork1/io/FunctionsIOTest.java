package ru.ssau.tk.respect.laboratorywork1.io;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.functions.*;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;

public class FunctionsIOTest {
    public TabulatedFunction arrayTabulated = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5.5, 6.9}, new double[]{7, 13, 15, 20, 25, 32});

    @Test
    public void testCharacterStreams() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output/test character streams.txt"));
        FunctionsIO.writeTabulatedFunction(bufferedWriter, arrayTabulated);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("output/test character streams.txt"));
        TabulatedFunction listTabulated = FunctionsIO.readTabulatedFunction(bufferedReader, new LinkedListTabulatedFunctionFactory());
        Assert.assertEquals(listTabulated.getCount(), arrayTabulated.getCount());
        for (int index = 0; index < listTabulated.getCount(); index++) {
            Assert.assertEquals(listTabulated.getX(index), arrayTabulated.getX(index));
            Assert.assertEquals(listTabulated.getY(index), arrayTabulated.getY(index));
        }
        Assert.assertThrows(FileNotFoundException.class, () -> new FileReader("input/test.txt"));
    }

    @Test
    public void testByteStreams() throws IOException {
        TabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(new double[]{2, 3, 5, 7, 9}, new double[]{5, 8, 17, 21, 15, 34});
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("temp/test byte streams.bin"));
        FunctionsIO.writeTabulatedFunction(bufferedOutputStream, tabulatedFunction);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("temp/test byte streams.bin"));
        TabulatedFunction listTabulated = FunctionsIO.readTabulatedFunction(bufferedInputStream, new LinkedListTabulatedFunctionFactory());
        Assert.assertEquals(listTabulated.getCount(), tabulatedFunction.getCount());
        for (int index = 0; index < listTabulated.getCount(); index++) {
            Assert.assertEquals(listTabulated.getX(index), tabulatedFunction.getX(index));
            Assert.assertEquals(listTabulated.getY(index), tabulatedFunction.getY(index));
        }
    }

    @Test
    public void testSerializeDeserialize() throws IOException, ClassNotFoundException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("temp/test serialize.bin"));
        FunctionsIO.serialize(bufferedOutputStream, arrayTabulated);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("temp/test serialize.bin"));
        TabulatedFunction listTabulated = FunctionsIO.deserialize(bufferedInputStream);
        Assert.assertEquals(listTabulated.getCount(), arrayTabulated.getCount());
        for (int index = 0; index < listTabulated.getCount(); index++) {
            Assert.assertEquals(listTabulated.getX(index), arrayTabulated.getX(index));
            Assert.assertEquals(listTabulated.getY(index), arrayTabulated.getY(index));
        }
        Assert.assertThrows(EOFException.class, () -> FunctionsIO.deserialize(bufferedInputStream));
    }
}