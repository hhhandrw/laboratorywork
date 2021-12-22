package ru.ssau.tk.respect.laboratorywork1.ui;

import ru.ssau.tk.respect.laboratorywork1.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.respect.laboratorywork1.functions.ArrayTabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.io.FunctionsIO;
import ru.ssau.tk.respect.laboratorywork1.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleOperationsWindow extends JDialog {

    private static final int FIRST_FUNCTION = 0;
    private static final int SECOND_FUNCTION = 1;
    private static final int RESULT_FUNCTION = 2;

    List<String> xValues = new ArrayList<>();
    List<String> yValues = new ArrayList<>();

    List<String> secondXValues = new ArrayList<>();
    List<String> secondYValues = new ArrayList<>();

    List<String> resultXValues = new ArrayList<>();
    List<String> resultYValues = new ArrayList<>();

    AbstractTableModel firstTableModel = new PartiallyEditable(xValues, yValues);
    JTable firstTable = new JTable(firstTableModel);

    AbstractTableModel secondTableModel = new PartiallyEditable(secondXValues, secondYValues);
    JTable secondTable = new JTable(secondTableModel);

    AbstractTableModel resultTableModel = new NotEditable(resultXValues, resultYValues);
    JTable resultTable = new JTable(resultTableModel);

    JComboBox<String> comboBox = new JComboBox<>(new String[]{"+", "-", "*", "÷"});

    JButton saveButton = new JButton("Сохранить");
    JButton uploadButton = new JButton("Загрузить");
    JButton createButton = new JButton("Создать");

    JButton saveButtonTwo = new JButton("Сохранить");
    JButton uploadButtonTwo = new JButton("Загрузить");
    JButton createButtonTwo = new JButton("Создать");

    JButton resultSaveButton = new JButton("Сохранить");
    JButton resultButton = new JButton("=");

    private final TabulatedFunctionFactory factory;
    private TabulatedFunction firstFunction;
    private TabulatedFunction secondFunction;
    private TabulatedFunction resultFunction;

    JFileChooser fileChooser;


    public SimpleOperationsWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());
        setSize(1000, 400);

        saveButton.setFocusPainted(false);
        uploadButton.setFocusPainted(false);
        createButton.setFocusPainted(false);
        saveButtonTwo.setFocusPainted(false);
        uploadButtonTwo.setFocusPainted(false);
        createButtonTwo.setFocusPainted(false);
        resultSaveButton.setFocusPainted(false);
        resultButton.setFocusPainted(false);

        comboBox.setPreferredSize(new Dimension(2, 2));
        comboBox.setFont(new Font("Consolas", Font.PLAIN, 18));
        resultButton.setFont(new Font("Consolas", Font.PLAIN, 20));

        getContentPane().add(resultButton);
        getContentPane().add(comboBox);
        getContentPane().add(saveButton);
        getContentPane().add(uploadButton);
        getContentPane().add(createButton);

        firstTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JScrollPane scrollPane = new JScrollPane(firstTable);
        JScrollPane scrollPaneTwo = new JScrollPane(secondTable);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(8)
                        .addComponent(scrollPane)
                        .addComponent(comboBox)
                        .addComponent(scrollPaneTwo)
                        .addComponent(resultButton)
                        .addComponent(resultScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(33)//.addGap(60)
                        .addComponent(createButtonTwo)
                        .addComponent(uploadButtonTwo)
                        .addComponent(saveButtonTwo)
                        .addGap(125)
                        .addComponent(resultSaveButton))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120)
                                .addComponent(comboBox)
                                .addGap(110)
                        )
                        .addComponent(scrollPaneTwo)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(130)
                                .addComponent(resultButton)
                        )
                        .addComponent(resultScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(60)
                        .addComponent(createButtonTwo)
                        .addComponent(uploadButtonTwo)
                        .addComponent(saveButtonTwo)
                        .addGap(60)
                        .addComponent(resultSaveButton))
        );
    }

    private void setValues(List<String> xValues, List<String> yValues, TabulatedFunction function) {
        xValues.clear();
        yValues.clear();
        for (int i = 0; i < function.getCount(); i++) {
            xValues.add(Double.toString(function.getX(i)));
            yValues.add(Double.toString(function.getY(i)));
        }
    }

    private void getPopupMenu(JButton button, List<String> xValues, List<String> yValues, AbstractTableModel
            tableModel, int flag) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem fromTable = new JMenuItem("из таблицы");
        JMenuItem fromFunction = new JMenuItem("из встроенной функции");

        fromTable.addActionListener(ee -> {
            Window window = new Window(factory);
            switch (flag) {
                case FIRST_FUNCTION:
                    firstFunction = window.getFunction();
                    break;
                case SECOND_FUNCTION:
                    secondFunction = window.getFunction();
            }

            setValues(xValues, yValues, window.getFunction());
            tableModel.fireTableDataChanged();
        });

        fromFunction.addActionListener(ee -> {
            SecondWindow window = new SecondWindow(factory);
            switch (flag) {
                case FIRST_FUNCTION:
                    firstFunction = window.getFunction();
                    break;
                case SECOND_FUNCTION:
                    secondFunction = window.getFunction();
            }
            setValues(xValues, yValues, window.getFunction());
            tableModel.fireTableDataChanged();
        });

        popupMenu.add(fromTable);
        popupMenu.addSeparator();
        popupMenu.add(fromFunction);
        popupMenu.show(button, button.getWidth() + 1, button.getHeight() / 30);
    }

    private void addButtonListeners() {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getPopupMenu(createButton, xValues, yValues, firstTableModel, FIRST_FUNCTION);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        createButtonTwo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getPopupMenu(createButtonTwo, secondXValues, secondYValues, secondTableModel, SECOND_FUNCTION);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        resultButton.addActionListener(e -> {
            try {
                TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService(factory);
                switch ((String) comboBox.getSelectedItem()) {
                    case "+":
                        resultFunction = operation.summarize(firstFunction, secondFunction);
                        break;
                    case "-":
                        resultFunction = operation.subtract(firstFunction, secondFunction);
                        break;
                    case "*":
                        resultFunction = operation.multiply(firstFunction, secondFunction);
                        break;
                    case "/":
                        resultFunction = operation.divide(firstFunction, secondFunction);
                }
                setValues(resultXValues, resultYValues, resultFunction);
                resultTableModel.fireTableDataChanged();
            } catch (NullPointerException exp) {
                ExceptionHandler.showMessage("Введите обе функции");
            } catch (InconsistentFunctionsException exp) {
                ExceptionHandler.showMessage(exp.getMessage());
            }
        });

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);

        uploadButton.addActionListener(e -> readFunction(FIRST_FUNCTION));
        uploadButtonTwo.addActionListener(e -> readFunction(SECOND_FUNCTION));
        saveButton.addActionListener(e -> writeFunction(FIRST_FUNCTION));
        uploadButtonTwo.addActionListener(e -> writeFunction(SECOND_FUNCTION));
        resultSaveButton.addActionListener(e -> writeFunction(RESULT_FUNCTION));
    }

        private void readFunction(int flag) {
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();

            if (file != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    ArrayTabulatedFunction function = (ArrayTabulatedFunction) FunctionsIO.readTabulatedFunction(reader, new ArrayTabulatedFunctionFactory());
                    switch (flag) {
                        case FIRST_FUNCTION:
                            firstFunction = function;
                            setValues(xValues, yValues, function);
                            firstTableModel.fireTableDataChanged();
                            break;
                        case SECOND_FUNCTION:
                            secondFunction = function;
                            setValues(secondXValues, secondYValues, function);
                            secondTableModel.fireTableDataChanged();
                    }
                } catch (IOException e) {
                    ExceptionHandler.showMessage(e.getMessage());
                }
            }
        }

        private void writeFunction(int flag) {
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();

            if (file != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    switch (flag) {
                        case FIRST_FUNCTION:
                            FunctionsIO.writeTabulatedFunction(writer, firstFunction);
                            break;
                        case SECOND_FUNCTION:
                            FunctionsIO.writeTabulatedFunction(writer, secondFunction);
                            break;
                        case RESULT_FUNCTION:
                            FunctionsIO.writeTabulatedFunction(writer, resultFunction);
                    }
                } catch (IOException e) {
                    ExceptionHandler.showMessage(e.getMessage());
                }
            }
    }

    public static void main(String[] args) {
        new SimpleOperationsWindow(new ArrayTabulatedFunctionFactory());
    }
}
