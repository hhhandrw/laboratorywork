package ru.ssau.tk.respect.laboratorywork1.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

    JButton arrayButton = new JButton("Создание функции из массивов");
    JButton buttonTF = new JButton("Создание табулированной функции");
    JButton operationButton = new JButton("Операции над функциями");
    JButton settingsButton = new JButton("Настройки");
    JButton exitButton = new JButton("Выход");
    JButton deriveButton = new JButton("Дифференцирование функции");

    public MainWindow() {
        super("MainWindow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        setSize(500, 500);
        setResizable(false);

        getContentPane().add(arrayButton);
        getContentPane().add(buttonTF);
        getContentPane().add(operationButton);
        getContentPane().add(settingsButton);
        getContentPane().add(exitButton);

        getContentPane().add(deriveButton);
        arrayButton.setFocusPainted(false);
        buttonTF.setFocusPainted(false);
        operationButton.setFocusPainted(false);
        settingsButton.setFocusPainted(false);
        exitButton.setFocusPainted(false);
        deriveButton.setFocusPainted(false);

        compose();
        addButtonListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(arrayButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(buttonTF)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(operationButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(deriveButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(settingsButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(exitButton)
                )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(70)
                .addComponent(arrayButton)
                .addGap(20)
                .addComponent(buttonTF)
                .addGap(20)
                .addComponent(operationButton)
                .addGap(20)
                .addComponent(deriveButton)
                .addGap(20)
                .addComponent(settingsButton)
                .addGap(20)
                .addComponent(exitButton)
        );
    }

    private void addButtonListeners() {
        arrayButton.addActionListener(e -> new Window(WindowWithSettings.getFactory()));
        buttonTF.addActionListener(e -> new SecondWindow(WindowWithSettings.getFactory()));
        operationButton.addActionListener(e -> new SimpleOperationsWindow(WindowWithSettings.getFactory()));
        settingsButton.addActionListener(e -> new WindowWithSettings());
        deriveButton.addActionListener(e -> new DerivationWindow(WindowWithSettings.getFactory()));
        exitButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
