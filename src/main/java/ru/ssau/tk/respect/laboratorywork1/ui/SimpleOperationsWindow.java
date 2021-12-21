package ru.ssau.tk.respect.laboratorywork1.ui;

import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleOperationsWindow extends JFrame {

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

    AbstractTableModel resultTableModel = new PartiallyEditable(resultXValues, resultYValues);
    JTable resultTable = new JTable(resultTableModel);

    JLabel label = new JLabel("=");
    JComboBox<String> comboBox = new JComboBox<>(new String[]{"+", "-", "*", "÷",});

    JButton saveButton = new JButton("Сохранить");
    JButton uploadButton = new JButton("Загрузить");
    JButton createButton = new JButton("Создать");

    JButton saveButtonTwo = new JButton("Сохранить");
    JButton uploadButtonTwo = new JButton("Загрузить");
    JButton createButtonTwo = new JButton("Создать");

    public SimpleOperationsWindow() {
        super("Operation Service");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        setSize(1000, 400);

        saveButton.setFocusPainted(false);
        uploadButton.setFocusPainted(false);
        createButton.setFocusPainted(false);

        comboBox.setPreferredSize(new Dimension(2, 2));
        label.setFont(new Font("Georgia", Font.BOLD, 24));

        getContentPane().add(label);
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
                        .addComponent(scrollPane)
                        .addComponent(comboBox)
                        .addComponent(scrollPaneTwo)
                        .addComponent(label)
                        .addComponent(resultScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(60)
                        .addComponent(createButtonTwo)
                        .addComponent(uploadButtonTwo)
                        .addComponent(saveButtonTwo)));
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
                                .addComponent(label)
                        )
                        .addComponent(resultScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(60)
                        .addComponent(createButtonTwo)
                        .addComponent(uploadButtonTwo)
                        .addComponent(saveButtonTwo)));
    }

    private void addButtonListeners() {
        createButton.addActionListener(e -> {
            Window window = new Window();
            TabulatedFunction function = window.getFunction();
            for (int i = 0; i < function.getCount(); i++) {
                xValues.add(Double.toString(function.getX(i)));
                yValues.add(Double.toString(function.getY(i)));
            }
            firstTableModel.fireTableDataChanged();
        });
    }

    public static void main(String[] args) {
        new SimpleOperationsWindow();
    }
}
