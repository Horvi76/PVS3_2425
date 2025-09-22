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

        JLabel temp = new JLabel();
        temp.setPreferredSize(new Dimension(500, 100));
        temp.setBackground(Color.blue);
        temp.setOpaque(true);

        JLabel alsoTemp = new JLabel();

        alsoTemp.setPreferredSize(new Dimension(100, 100));
        alsoTemp.setBackground(Color.red);
        alsoTemp.setOpaque(true);

        this.add(temp);
        this.add(alsoTemp);

        this.pack();
    }
}

class ResultWindow extends JFrame {
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
