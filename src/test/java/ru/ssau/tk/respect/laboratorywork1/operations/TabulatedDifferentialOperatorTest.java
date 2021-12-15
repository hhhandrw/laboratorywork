package ru.ssau.tk.respect.laboratorywork1.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.respect.laboratorywork1.functions.*;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {

    private final double[] xValues = new double[]{1, 3, 5, 7, 9};
    private final double[] yValues = new double[]{3, 9, 15, 21, 27};

    private final MathFunction firstMathFunction = new HalfFunction();
    private final MathFunction secondMathFunction = new IdentityFunction();

    private TabulatedFunction createFirstFunction() {
        return new LinkedListTabulatedFunction(firstMathFunction, 2, 6, 9);
    }

    private TabulatedFunction createSecondFunction() {
        return new ArrayTabulatedFunction(secondMathFunction, 2, 6, 9);
    }

    private TabulatedFunction createFromArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testDerive() {
        TabulatedFunction firstFunction = createFirstFunction();
        TabulatedDifferentialOperator firstListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction firstListDerivative = firstListOperator.derive(firstFunction);

        assertEquals(firstListDerivative.getX(0), 2.0);
        assertEquals(firstListDerivative.getX(5), 4.5);
        assertEquals(firstListDerivative.getY(0), 0.5);
        assertEquals(firstListDerivative.getY(5), 0.5);
        assertTrue(firstListDerivative instanceof LinkedListTabulatedFunction);

        TabulatedDifferentialOperator firstArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction firstArrayDerivative = firstArrayOperator.derive(firstFunction);

        assertEquals(firstArrayDerivative.getX(0), 2.0);
        assertEquals(firstArrayDerivative.getX(5), 4.5);
        assertEquals(firstArrayDerivative.getY(0), 0.5);
        assertEquals(firstArrayDerivative.getY(5), 0.5);
        assertTrue(firstArrayDerivative instanceof ArrayTabulatedFunction);

        TabulatedFunction secondFunction = createSecondFunction();
        TabulatedDifferentialOperator secondListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction secondListDerivative = secondListOperator.derive(secondFunction);

        assertEquals(secondListDerivative.getX(0), 2.0);
        assertEquals(secondListDerivative.getX(4), 4.0);
        assertEquals(secondListDerivative.getY(0), 1.0);
        assertEquals(secondListDerivative.getY(4), 1.0);
        assertTrue(secondListDerivative instanceof LinkedListTabulatedFunction);

        TabulatedDifferentialOperator secondArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction secondArrayDerivative = secondArrayOperator.derive(secondFunction);

        assertEquals(secondArrayDerivative.getX(0), 2.0);
        assertEquals(secondArrayDerivative.getX(4), 4.0);
        assertEquals(secondArrayDerivative.getY(0), 1.0);
        assertEquals(secondArrayDerivative.getY(4), 1.0);
        assertTrue(secondArrayDerivative instanceof ArrayTabulatedFunction);

        TabulatedFunction thirdFunction = createFromArray();
        TabulatedDifferentialOperator thirdListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction thirdListDerivative = thirdListOperator.derive(thirdFunction);

        assertEquals(thirdListDerivative.getX(1), 3.0);
        assertEquals(thirdListDerivative.getX(4), 9.0);
        assertEquals(thirdListDerivative.getY(1), 3.0);
        assertEquals(thirdListDerivative.getY(4), 3.0);
        assertTrue(thirdListDerivative instanceof LinkedListTabulatedFunction);

        TabulatedDifferentialOperator thirdArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction thirdArrayDerivative = thirdArrayOperator.derive(thirdFunction);

        assertEquals(thirdArrayDerivative.getX(1), 3.0);
        assertEquals(thirdArrayDerivative.getX(4), 9.0);
        assertEquals(thirdArrayDerivative.getY(1), 3.0);
        assertEquals(thirdArrayDerivative.getY(4), 3.0);
        assertTrue(thirdArrayDerivative instanceof ArrayTabulatedFunction);
    }

    @Test
    public void testDeriveSynchronously() {
        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new double[]{2., 3., 4., 5., 6.}, new double[]{4., 9., 16., 25., 37.});
        TabulatedDifferentialOperator differentialOperatorList = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionList = differentialOperatorList.deriveSynchronously(linkedListTabulatedFunction);

        assertTrue(differentialFunctionList instanceof LinkedListTabulatedFunction);

        for (int i = 0; i < differentialFunctionList.getCount(); i++) {
            assertEquals(differentialFunctionList.getX(i), (2. + (double) i));
        }

        assertEquals(differentialFunctionList.getY(0), 5.);
        assertEquals(differentialFunctionList.getY(1), 7.);
        assertEquals(differentialFunctionList.getY(2), 9.);
        assertEquals(differentialFunctionList.getY(3), 12.);
        assertEquals(differentialFunctionList.getY(4), 12.);

        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new double[]{4., 5., 6., 7., 8.}, new double[]{16., 25., 36., 49., 64.});
        TabulatedDifferentialOperator differentialOperatorArray = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionArray = differentialOperatorArray.deriveSynchronously(arrayTabulatedFunction);
        assertTrue(differentialFunctionArray instanceof ArrayTabulatedFunction);

        for (int i = 0; i < differentialFunctionArray.getCount(); i++) {
            assertEquals(differentialFunctionArray.getX(i), (4. + (double) i));
        }

        assertEquals(differentialFunctionArray.getY(0), 9.);
        assertEquals(differentialFunctionArray.getY(1), 11.);
        assertEquals(differentialFunctionArray.getY(2), 13.);
        assertEquals(differentialFunctionArray.getY(3), 15.);
        assertEquals(differentialFunctionArray.getY(4), 15.);
    }
}