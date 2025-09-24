package gui;

import basics.GridPane;

import javax.swing.*;
import java.awt.*;

public class Multiples extends JFrame {

    public Multiples() throws HeadlessException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        JTextField textInput = new JTextField("number here");
        JButton draw = new JButton("Draw");

        textInput.setFont(new Font("Consolas", Font.BOLD, 30));
        textInput.setHorizontalAlignment(SwingConstants.CENTER);
        draw.setFont(new Font("Consolas", Font.BOLD, 30));

        draw.addActionListener(e -> {
            int number;
            try {
                number = Integer.parseInt(textInput.getText());
                new GridPane(number).setVisible(true);
            } catch (NumberFormatException ne){
                JOptionPane.showMessageDialog(null, "Not a number");
            }
        });

        this.add(textInput);
        this.add(draw);
        this.pack();
    }

    public static void main(String[] args) {
        new Multiples().setVisible(true);
    }
}
