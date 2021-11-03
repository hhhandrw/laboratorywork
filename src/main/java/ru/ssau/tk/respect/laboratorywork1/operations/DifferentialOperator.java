package ru.ssau.tk.respect.laboratorywork1.operations;

import ru.ssau.tk.respect.laboratorywork1.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {

    T derive(T function);
}
