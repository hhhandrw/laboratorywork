package ru.ssau.tk.respect.laboratorywork1.concurrent;

import ru.ssau.tk.respect.laboratorywork1.functions.ConstantFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;

public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);
        Thread thread1 = new Thread(new MultiplyingTask(tabulatedFunction));
        Thread thread2 = new Thread(new MultiplyingTask(tabulatedFunction));
        Thread thread3 = new Thread(new AddingTask(tabulatedFunction));

        thread1.start();
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(tabulatedFunction);
    }
}
