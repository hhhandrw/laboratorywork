package ru.ssau.tk.respect.laboratorywork1.ui;

import ru.ssau.tk.respect.laboratorywork1.functions.TabulatedFunction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    JButton resultSaveButton = new JButton("Сохранить");

    public SimpleOperationsWindow() {
        super("Operation Service");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        setSize(985, 400);

        saveButton.setFocusPainted(false);
        uploadButton.setFocusPainted(false);
        createButton.setFocusPainted(false);
        saveButtonTwo.setFocusPainted(false);
        uploadButtonTwo.setFocusPainted(false);
        createButtonTwo.setFocusPainted(false);
        resultSaveButton.setFocusPainted(false);

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
                        .addGap(8)
                        .addComponent(scrollPane)
                        .addComponent(comboBox)
                        .addComponent(scrollPaneTwo)
                        .addComponent(label)
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

    private void addButtonListeners() {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem fromTable = new JMenuItem("из таблицы");
                    JMenuItem fromFunction = new JMenuItem("из функции");

                    fromTable.addActionListener(ee -> {
                        Window window = new Window();
                        setValues(xValues, yValues, window.function);
                        firstTableModel.fireTableDataChanged();
                    });

                    fromFunction.addActionListener(ee -> {
                        SecondWindow window = new SecondWindow();
                        setValues(xValues, yValues, window.function);
                        firstTableModel.fireTableDataChanged();
                    });

                    popupMenu.add(fromTable);
                    popupMenu.addSeparator();
                    popupMenu.add(fromFunction);
                    popupMenu.show(createButton, createButton.getWidth() + 1, createButton.getHeight() / 30);
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
    }

    public static void main(String[] args) {
        new SimpleOperationsWindow();
    }
}
