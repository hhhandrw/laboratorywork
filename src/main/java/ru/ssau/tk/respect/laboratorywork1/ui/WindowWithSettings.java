package ru.ssau.tk.respect.laboratorywork1.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class WindowWithSettings extends JDialog {
    JTabbedPane tabbedPane = new JTabbedPane();

    public WindowWithSettings() {
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 400);

        tabbedPane.setPreferredSize(new Dimension(300, 300));
        JPanel design = new FirstPanel();
        JPanel factorySelection = new SecondPanel();
        tabbedPane.addTab("Дизайн", design);
        tabbedPane.addTab("Выбор фабрики", factorySelection);
        tabbedPane.setBackground(Color.lightGray);
        tabbedPane.setForeground(Color.white);

        getContentPane().add(tabbedPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static class ColorOption extends JRadioButton {
        private final Color color;
        public ColorOption(String colorName, Color color) {
            super((colorName));
            this.color = color;
        }
        public Color getColor() {
            return color;
        }
    }

    class FirstPanel extends JPanel{
        ArrayList<ColorOption> colorOptions = new ArrayList<>();

        public FirstPanel() {
            colorOptions.add(new ColorOption("Черный", Color.BLACK));
            colorOptions.add(new ColorOption("Белый", Color.WHITE));
            colorOptions.add(new ColorOption("Светло-серый", Color.LIGHT_GRAY));
            colorOptions.add(new ColorOption("Серый", Color.GRAY));
            colorOptions.add(new ColorOption("Тёмно-серый", Color.DARK_GRAY));
            colorOptions.add(new ColorOption("Голубой", Color.CYAN));
            ButtonGroup buttonGroup = new ButtonGroup();
            for (ColorOption colorOption : colorOptions) {
                buttonGroup.add(colorOption);
                setLayout(new FlowLayout());
                add(colorOption);
                colorOption.addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        getContentPane().setBackground(colorOption.getColor());
                    }
                });
            }
        }
    }

    static class SecondPanel extends JPanel {
        ButtonGroup group = new ButtonGroup();

        public SecondPanel() {
            JRadioButton firstButton = new JRadioButton("Массивы", true);
            group.add(firstButton);
            JRadioButton secondButton = new JRadioButton("Связный список", false);
            group.add(secondButton);
            add(firstButton);
            add(secondButton);
        }
    }

    public static void main(String[] args) {
        new WindowWithSettings();
    }
}
