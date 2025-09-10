package gui;

import javax.swing.*;
import java.awt.*;

public class Flow extends JFrame {

    public Flow(){
        this.setSize(600,200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 20));

        for (int i = 0; i < 6; i++) {
            JButton button = new JButton(String.valueOf(i+1));
//            button.setPreferredSize(new Dimension(150, 150));
            button.setFont(new Font("Consolas", Font.BOLD, 32));
            button.setBackground(Color.blue);
            button.setForeground(Color.white);
            this.add(button);
        }
    }

    public static void main(String[] args) {
        new Flow().setVisible(true);
    }
}
