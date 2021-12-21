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

    public MainWindow() {
        super("MainWindow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        setSize(1280, 720);

        getContentPane().add(arrayButton);
        getContentPane().add(buttonTF);
        getContentPane().add(operationButton);
        getContentPane().add(settingsButton);
        getContentPane().add(exitButton);

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
                .addComponent(arrayButton)
                .addComponent(buttonTF)
                .addComponent(operationButton)
                .addComponent(settingsButton)
                .addComponent(exitButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(arrayButton)
                .addComponent(buttonTF)
                .addComponent(operationButton)
                .addComponent(settingsButton)
                .addComponent(exitButton)
        );
    }

    private void addButtonListeners() {
        arrayButton.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = -8825708290900274482L;

            @Override
            public void actionPerformed(ActionEvent e) {
                new Window();
            }
        });

        buttonTF.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = 753806851377386060L;

            @Override
            public void actionPerformed(ActionEvent e) {
                new SecondWindow();
            }
        });
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
