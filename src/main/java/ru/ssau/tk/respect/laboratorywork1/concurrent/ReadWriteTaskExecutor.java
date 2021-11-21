package ru.ssau.tk.respect.laboratorywork1.concurrent;

import ru.ssau.tk.respect.laboratorywork1.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.ZeroFunction;

import java.util.ArrayList;
import java.util.List;


public class ReadWriteTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            threads.add(new Thread(new ReadWriteTask(function)));
        }
        for (Thread t : threads) {
            t.start();
        }
        Thread.sleep(2000);
        System.out.println(function);
    }
}
