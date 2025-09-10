package gui;

import javax.swing.*;
import java.awt.*;

public class Panels extends JFrame {

    public Panels() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);

        //panely
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        this.setLayout(null);

        redPanel.setBounds(50, 50, 300, 400);

        redPanel.setLayout(new FlowLayout());

        for (int i = 0; i < 20; i++) {
            redPanel.add(new JButton("X"));
        }

        this.add(redPanel);
    }

    public static void main(String[] args) {
        new Panels().setVisible(true);
    }
}
