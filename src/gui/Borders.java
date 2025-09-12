package gui;

import javax.swing.*;
import java.awt.*;

public class Borders extends JFrame {

    public Borders() {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //---
        this.setLayout(new BorderLayout());

        JPanel redPanel = new JPanel();
        redPanel.setPreferredSize(new Dimension(100, 100));
        redPanel.setBackground(Color.red);

        JPanel greenPanel = new JPanel();
        greenPanel.setPreferredSize(new Dimension(100, 100));
        greenPanel.setBackground(Color.green);

        JPanel bluePanel = new JPanel();
        bluePanel.setPreferredSize(new Dimension(100, 100));
        bluePanel.setBackground(Color.blue);

        JPanel yellowPanel = new JPanel();
        yellowPanel.setPreferredSize(new Dimension(100, 100));
        yellowPanel.setBackground(Color.yellow);

        this.add(redPanel, BorderLayout.WEST);
        this.add(bluePanel, BorderLayout.EAST);
        this.add(greenPanel, BorderLayout.SOUTH);
        this.add(yellowPanel, BorderLayout.NORTH);

        JButton rotate = new JButton("Rotate");
        rotate.addActionListener(e -> {
            Color c = redPanel.getBackground();
            redPanel.setBackground(greenPanel.getBackground());
            greenPanel.setBackground(bluePanel.getBackground());
            bluePanel.setBackground(yellowPanel.getBackground());
            yellowPanel.setBackground(c);
        });
        this.add(rotate, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Borders().setVisible(true);
    }
}
