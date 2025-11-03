package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixPaint extends JFrame {
    JLabel[][] labels;
    final static int MATRIX_DIMENSION = 10;
    JButton button;

    MatrixPaint() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel matrixCanvas = new JPanel();
        matrixCanvas.setLayout(new GridLayout(MATRIX_DIMENSION, MATRIX_DIMENSION));
        button = new JButton("Paint");
        this.add(button, BorderLayout.NORTH);
        this.add(matrixCanvas, BorderLayout.CENTER);

        Border border = BorderFactory.createLineBorder(Color.black, 1);
        labels = new JLabel[MATRIX_DIMENSION][MATRIX_DIMENSION];
        for (int i = 0; i < MATRIX_DIMENSION; i++) {
            for (int j = 0; j < MATRIX_DIMENSION; j++) {
                labels[i][j] = new JLabel(" ");
                labels[i][j].setBorder(border);
                labels[i][j].setOpaque(true);// bude se hodit :)
                labels[i][j].setHorizontalAlignment(JLabel.CENTER);
                labels[i][j].setVerticalTextPosition(JLabel.CENTER);
                matrixCanvas.add(labels[i][j]);
            }
        }

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setText("Painting...");
                button.setEnabled(false);
                //button.paintImmediately(0, 0, getWidth(), getHeight());
                for (int i = 0; i  < MATRIX_DIMENSION; i++) {
                    for (int j = 0; j < MATRIX_DIMENSION; j++) {
                        labels[i][j].setBackground(Color.blue);
                    }
                }
            }
        });
        this.pack();
    }

    public static void main(String[] args) {
        MatrixPaint matrix = new MatrixPaint();
        matrix.setVisible(true);
    }
}
