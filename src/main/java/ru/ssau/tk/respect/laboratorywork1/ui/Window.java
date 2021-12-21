package ru.ssau.tk.respect.laboratorywork1.ui;

import ru.ssau.tk.respect.laboratorywork1.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.respect.laboratorywork1.functions.ArrayTabulatedFunction;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {

    List<String> xValues = new ArrayList<>();
    List<String> yValues = new ArrayList<>();

    AbstractTableModel tableModel = new Table(xValues, yValues);
    JTable table = new JTable(tableModel);
    JLabel label = new JLabel("Количество точек:");

    JTextField textField = new JTextField("2");
    JButton addButton = new JButton("Добавить");
    JButton createButton = new JButton("Создать");
    JButton refreshButton = new JButton("Очистить");

    public Window() {
        super("Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 300);

        addButton.setFocusPainted(false);
        refreshButton.setFocusPainted(false);
        createButton.setFocusPainted(false);

        getContentPane().add(label);
        getContentPane().add(textField);
        getContentPane().add(addButton);
        getContentPane().add(createButton);
        getContentPane().add(refreshButton);

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
                )
                .addComponent(tableScrollPane)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addComponent(refreshButton)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(addButton)
                )
                .addComponent(tableScrollPane)
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(refreshButton)
                )
        );
    }

    private void addButtonListeners() {
        class AddingAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = Integer.parseInt(textField.getText());
                    if (size < 0) {
                        ExceptionHandler.showMessage("Введите положительное число.");
                    }
                    for (int i = 0; i < size; i++) {
                        xValues.add(" ");
                        yValues.add(" ");
                    }
                    refreshButton.setEnabled(true);
                    tableModel.fireTableDataChanged();
                } catch (NumberFormatException exception) {
                    ExceptionHandler.showMessage("Введите целое число.");
                }
            }
        }
        refreshButton.setEnabled(false);
        textField.addActionListener(new AddingAction());
        addButton.addActionListener(new AddingAction());
        createButton.addActionListener(e -> {
            try {
                int size = xValues.size();
                double[] x = new double[size];
                double[] y = new double[size];
                for (int i = 0; i != size; i++) {
                    x[i] = Double.parseDouble(xValues.get(i));
                    y[i] = Double.parseDouble(yValues.get(i));
                }
                ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                ArrayTabulatedFunction function = (ArrayTabulatedFunction) factory.create(x, y);
                System.out.println(function);
                dispose();
            } catch (NumberFormatException exp) {
                ExceptionHandler.showMessage("Введите число в виде десятичной дроби через точку");
            } catch (ArrayIsNotSortedException exp) {
                ExceptionHandler.showMessage("Некорректные данные: значения X должны располагаться по возрастанию");
            } catch (IllegalArgumentException exp) {
                ExceptionHandler.showMessage("Невозможно создать функцию менее чем из двух точек");
            }
        });

        refreshButton.addActionListener(e -> {
            int flag = JOptionPane.showConfirmDialog(null, "Вы уверены?", "Предупреждение", JOptionPane.YES_NO_OPTION);
            if (flag == 0) {
                int size = xValues.size();
                xValues.clear();
                yValues.clear();
                for (int i = 0; i < size; i++) {
                    xValues.add(" ");
                    yValues.add(" ");
                }
                tableModel.fireTableDataChanged();
            }
        });

        table.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
            private JPopupMenu getPopupMenu() {
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem deleteItem = new JMenuItem("Удалить");
                JMenuItem cleanItem = new JMenuItem("Очистить");
                deleteItem.addActionListener(e -> {
                    xValues.remove(table.getSelectedRow());
                    yValues.remove(table.getSelectedRow());
                    if (xValues.isEmpty()) {
                        refreshButton.setEnabled(false);
                    }
                    tableModel.fireTableDataChanged();
                });
                cleanItem.addActionListener(e -> {
                    xValues.set(table.getSelectedRow(), " ");
                    yValues.set(table.getSelectedRow(), " ");
                    tableModel.fireTableDataChanged();
                });
                popupMenu.add(deleteItem);
                popupMenu.add(cleanItem);
                return popupMenu;
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
        new Window();
    }
}
