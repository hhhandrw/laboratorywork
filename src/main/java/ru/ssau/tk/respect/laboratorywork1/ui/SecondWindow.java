package ru.ssau.tk.respect.laboratorywork1.ui;

import ru.ssau.tk.respect.laboratorywork1.functions.*;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.respect.laboratorywork1.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SecondWindow extends JDialog {

    JLabel label = new JLabel("Введите количество точек разбиения:");
    JTextField textField = new JTextField("");
    JLabel secondLabel = new JLabel("Интервал с");
    JTextField secondTextField = new JTextField("");
    JLabel thirdLabel = new JLabel("по");
    JTextField thirdTextField = new JTextField("");
    JComboBox<String> comboBox = new JComboBox<>(new String[]{
            "Единичная функция", "Квадратичная функция", "Константная функция", "Нулевая функция", "Тангенсальная функция", "Тождественная функция", "Функция деления на 2"
    });
    JButton createButton = new JButton("Создать");

    private TabulatedFunction function;
    private final TabulatedFunctionFactory factory;

    public SecondWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;

        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 200);
        createButton.setFocusPainted(false);

        getContentPane().add(label);
        getContentPane().add(textField);
        getContentPane().add(secondLabel);
        getContentPane().add(secondTextField);
        getContentPane().add(thirdLabel);
        getContentPane().add(thirdTextField);
        getContentPane().add(comboBox);
        getContentPane().add(createButton);

        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public TabulatedFunction getFunction() {
        return function;
    }

    private void createFunction() {
        Map<String, MathFunction> functionsMap = new HashMap<>();
        functionsMap.put("Единичная функция", new UnitFunction());
        functionsMap.put("Квадратичная функция", new SqrFunction());
        functionsMap.put("Константная функция", new ConstantFunction(3));
        functionsMap.put("Нулевая функция", new ZeroFunction());
        functionsMap.put("Тангенсальная функция", new TanFunction());
        functionsMap.put("Тождественная функция", new IdentityFunction());
        functionsMap.put("Функция деления на 2", new HalfFunction());

        String functionName = (String) comboBox.getSelectedItem();
        MathFunction selectedFunction = functionsMap.get(functionName);
        double from = Double.parseDouble(secondTextField.getText());
        double to = Double.parseDouble(thirdTextField.getText());
        int count = Integer.parseInt(textField.getText());

        function = factory.createFromFunction(selectedFunction, from, to, count);
        System.out.println(function);
    }

    private void addButtonListeners() {
        createButton.addActionListener(e -> {
            try {
                int size = Integer.parseInt(textField.getText());
                if (size <= 0) {
                    ExceptionHandler.showMessage("Введите положительное число");
                }
                createFunction();
                dispose();
            } catch (NumberFormatException exp) {
                ExceptionHandler.showMessage("Введите целое число");
            } catch (IllegalArgumentException exp) {
                ExceptionHandler.showMessage("Введите правильный интервал");
            }
        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(textField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(secondLabel)
                        .addComponent(secondTextField)
                        .addComponent(thirdLabel)
                        .addComponent(thirdTextField))
                .addComponent(comboBox)
                .addComponent(createButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(textField))
                .addGroup(layout.createParallelGroup()
                        .addComponent(secondLabel)
                        .addComponent(secondTextField)
                        .addComponent(thirdLabel)
                        .addComponent(thirdTextField))
                .addComponent(comboBox)
                .addComponent(createButton)
        );
    }

    public static void main(String[] args) {
        new SecondWindow(new ArrayTabulatedFunctionFactory());
    }
}
