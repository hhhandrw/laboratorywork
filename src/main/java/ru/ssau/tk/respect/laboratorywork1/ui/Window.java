package ru.ssau.tk.respect.laboratorywork1.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {

    List<String> xValues = new ArrayList<>();
    List<String> yValues = new ArrayList<>();

    AbstractTableModel tableModel = new Table(xValues, yValues);
    JTable table = new JTable(tableModel);
    JLabel label = new JLabel("Введите количество точек:");

    JTextField textField = new JTextField("");
    JButton addButton = new JButton("Добавить");
    JButton createButton = new JButton("Создать");

    public Window() {
        super("Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(800, 400);

        getContentPane().add(label);
        getContentPane().add(textField);
        getContentPane().add(addButton);
        getContentPane().add(createButton);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        JScrollPane tableScrollPane = new JScrollPane(table);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(addButton)
                        .addComponent(createButton))
                .addComponent(tableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(addButton)
                        .addComponent(createButton))
                .addComponent(tableScrollPane)
        );
    }

    private void addButtonListeners() {
        addButton.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = -6411212203329206914L;

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Integer.parseInt(textField.getText()); i++) {
                    xValues.add(" ");
                    yValues.add(" ");
                }
                tableModel.fireTableDataChanged();
            }
        });

        createButton.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = -2698011746007293816L;

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new Window();
    }
}
