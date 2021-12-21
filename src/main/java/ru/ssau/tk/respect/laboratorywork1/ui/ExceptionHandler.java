package ru.ssau.tk.respect.laboratorywork1.ui;


import ru.ssau.tk.respect.laboratorywork1.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.respect.laboratorywork1.exceptions.InconsistentFunctionsException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ExceptionHandler extends JDialog {

    ExceptionHandler(Component parent, Exception e) {
        getErrorWindow(parent, e);
        setModal(true);
    }

    public void getErrorWindow(Component parent, Exception e) {
        String message = MessageForError(e);
        JOptionPane.showMessageDialog(parent, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private String MessageForError(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат данных";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Элементы X должны быть упорядочены";
        }
        if (e instanceof NullPointerException) {
            return "Возникла ошибка";
        }
        if (e instanceof IOException) {
            return "Не удалось прочитать/записать файл";
        }
        if (e instanceof InconsistentFunctionsException) {
            return "Абсциссы функций не совпадают";
        }
        return "Неверные данные";
    }
}
