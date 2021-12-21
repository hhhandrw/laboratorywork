package ru.ssau.tk.respect.laboratorywork1.ui;

import javax.swing.*;

public class ExceptionHandler {
    private ExceptionHandler() {
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}