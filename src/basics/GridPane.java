package basics;

import javax.swing.*;
import java.awt.*;

public class GridPane extends JFrame {

    final static int DIMENSION = 10;

    public GridPane() throws HeadlessException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < DIMENSION*DIMENSION; i++) {
            this.add(new Piece(Color.blue));
        }
        this.setSize(450, 450);
//        this.pack();
    }

    public static void main(String[] args) {
        new GridPane().setVisible(true);
    }
}
class Piece extends JLabel{
    public Piece(Color color){
        this.setText(" ");
        this.setFont(new Font("Consolas", Font.BOLD, 20));
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.setOpaque(true);
        this.setBackground(color);
    }
}
