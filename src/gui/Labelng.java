package gui;

import javax.swing.*;
import java.awt.*;

public class Labelng extends JFrame {

    public Labelng() {
        this.setSize(750, 400);
        this.setTitle("First window example");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLabel label = new JLabel("TEST");


        label.setForeground(new Color(0xff050f));
        label.setBackground(Color.black);
        label.setOpaque(true);


        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 64));

        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        this.setLayout(null);
        label.setBounds(this.getWidth()/2, this.getHeight()/2, 200, 200);
        this.add(label);
//        this.add(new JLabel("ANOTher"));
    }

    public static void main(String[] args) {
        new Labelng().setVisible(true);
    }
}
