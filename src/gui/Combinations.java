package gui;

import javax.swing.*;
import java.awt.*;

public class Combinations extends JFrame {
    Combinations(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        String[] choices = {"Borders", "Grids", "Flows"};

        JComboBox<String> comboBox = new JComboBox<>(choices);

        comboBox.addActionListener(e -> {
            System.out.println(comboBox.getSelectedIndex());
            System.out.println(comboBox.getSelectedItem());
        });

        JButton windowButton = new JButton("Open");

        windowButton.addActionListener(e -> {
            JFrame ref = null;
            switch (comboBox.getSelectedIndex()){
                case 0:
                    ref = new Borders();
                    break;
                case 1:
                   ref = new Grids();
                    break;
                case 2:
                   ref = new Flow();
                    break;
                default:
                    System.out.println("Not implemented :(");
            }
            if (ref != null){
                ref.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                ref.setVisible(true);
            }
        });

        comboBox.addItem("Last one");

        this.add(comboBox);
        this.add(windowButton);
        this.pack();
    }

    public static void main(String[] args) {
        new Combinations().setVisible(true);
    }
}
