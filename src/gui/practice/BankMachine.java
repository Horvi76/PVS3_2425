package gui.practice;

import javax.swing.*;
import java.awt.*;

public class BankMachine {
    public static void main(String[] args) {
        new InputWindow().setVisible(true);
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
                if (number <= 0) {
                    JOptionPane.showMessageDialog(null, "Zadejte kladne cislo");
                } else {
                    new ResultWindow(number).setVisible(true);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Zadejte cislo");
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

    static final int[] NOMINALS = {5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};

    int number;
    static void getNominals(int number, JPanel panel) {
        System.out.println("Number: " + number);
        for (int i = 0, count = 0; i < NOMINALS.length; i++, count = 0) {
            while (number >= NOMINALS[i]) {
                number -= NOMINALS[i];
                count++;
            }
            panel.add(new BankTile(NOMINALS[i], count));
            System.out.println(count + "x " + NOMINALS[i]);
        }
        System.out.println();
    }

    public ResultWindow(int number) {
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.number = number;
        JLabel totalValue = new JLabel(String.valueOf(number));
        totalValue.setPreferredSize(new Dimension(400, 100));
        totalValue.setHorizontalAlignment(SwingConstants.CENTER);
        totalValue.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        totalValue.setFont(new Font("Consolas", Font.BOLD, 32));

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3,4, 3, 3));
        gridPanel.setPreferredSize(new Dimension(400, 300));

        this.add(totalValue, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);

        getNominals(number, gridPanel);

        this.pack();
    }
} class BankTile extends JPanel{

    BankTile(int value, int amount){
        this.setLayout(new GridLayout(2,1));
        JLabel valueLabel = new JLabel(String.valueOf(value));
        JLabel amountLabel = new JLabel(amount + "x");

        valueLabel.setFont(new Font("Consolas", Font.BOLD, 24));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setFont(new Font("Consolas", Font.BOLD, 24));
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        amountLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        this.setOpaque(true);
//        valueLabel.setOpaque(true);
//        amountLabel.setOpaque(true);


        if (amount > 0){
            this.setBackground(Color.green);
        } else {
            this.setBackground(Color.red);
        }
        this.add(amountLabel);
        this.add(valueLabel);
    }
}
