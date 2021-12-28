package ru.ssau.tk.respect.laboratorywork1.ui;

import ru.ssau.tk.respect.laboratorywork1.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.respect.laboratorywork1.exceptions.WrongNumberOfElementsException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ErrorWindow extends JDialog {

    ErrorWindow(Component parent, Exception e) {
        getErrorWindow(parent, e);
        setModal(true);
    }

    public void getErrorWindow(Component parent, Exception e) {
        String message = MessageForException(e);
        JOptionPane.showMessageDialog(parent, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private String MessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат данных!";
        }
        if (e instanceof WrongNumberOfElementsException) {
            return "Вы ввели неверное значение!";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Элементы X должны быть упорядочены!";
        }
        if (e instanceof NullPointerException) {
            return "Что-то пошло не так...";
        }
        if (e instanceof IOException)
            return "Не удалось прочитать/записать файл";
        return "Неверные данные!";
    }
}