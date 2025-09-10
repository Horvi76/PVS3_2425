package gui;

import javax.swing.*;
import java.awt.*;

public class BordersTBD extends JFrame {

    public BordersTBD() {
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

        JPanel centrePanel = new JPanel();
        centrePanel.setBackground(Color.magenta);

        centrePanel.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setPreferredSize(new Dimension(50, 50));
        panel1.setBackground(Color.lightGray);
        panel2.setPreferredSize(new Dimension(50, 50));
        panel2.setBackground(Color.darkGray);
        panel3.setPreferredSize(new Dimension(50, 50));
        panel3.setBackground(Color.black);
        panel4.setPreferredSize(new Dimension(50, 50));
        panel4.setBackground(Color.CYAN);

        this.add(centrePanel, BorderLayout.CENTER);
        centrePanel.add(panel3, BorderLayout.NORTH);
        centrePanel.add(panel1, BorderLayout.SOUTH);
        centrePanel.add(panel4, BorderLayout.WEST);
        centrePanel.add(panel2, BorderLayout.EAST);

        JPanel centreCentrePanel = new JPanel();
        centreCentrePanel.setBackground(Color.white);
        centreCentrePanel.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
        centrePanel.add(centreCentrePanel, BorderLayout.CENTER);

        centreCentrePanel.add(new JButton("NECO"));
    }

    public static void main(String[] args) {
        new BordersTBD().setVisible(true);
    }
}
