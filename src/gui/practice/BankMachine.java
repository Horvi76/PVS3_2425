package gui.practice;

import javax.swing.*;
import java.awt.*;

public class BankMachine {
    public static void main(String[] args) {
        new InputWindow().setVisible(true);
        new ResultWindow().setVisible(true);
    }
}

class InputWindow extends JFrame {

    public InputWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());

        JTextField input = new JTextField("Value");
        input.setPreferredSize(new Dimension(500, 100));
        input.setFont(new Font("Consolas", Font.BOLD, 32));
        input.setHorizontalAlignment(SwingConstants.CENTER);


        JButton inButton = new JButton("Go");
        inButton.addActionListener(e -> {
            try {
                int number = Integer.parseInt(input.getText());
                if (number <= 0){
                    JOptionPane.showMessageDialog(null,"Zadejte kladne cislo");
                } else {
                   ResultWindow.getNominals(number);
                }
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Zadejte cislo");
            }
        });
        inButton.setPreferredSize(new Dimension(100, 100));
        inButton.setFont(new Font("Consolas", Font.BOLD, 32));

        this.add(input);
        this.add(inButton);

        this.pack();
    }
}

class ResultWindow extends JFrame {


    static void getNominals(int number){
        //print
        System.out.println();
    }
    public ResultWindow() {
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        JLabel temp = new JLabel();
        temp.setPreferredSize(new Dimension(400, 100));
        temp.setBackground(Color.green);
        temp.setOpaque(true);

        JLabel alsoTemp = new JLabel();

        alsoTemp.setPreferredSize(new Dimension(400, 300));
        alsoTemp.setBackground(Color.yellow);
        alsoTemp.setOpaque(true);

        this.add(temp, BorderLayout.NORTH);
        this.add(alsoTemp, BorderLayout.CENTER);


        this.pack();
    }

}
