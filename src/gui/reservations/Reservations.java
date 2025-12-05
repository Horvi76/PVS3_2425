package gui.reservations;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class Reservations extends JFrame implements Serializable {
    @Serial
    private final static long serialVersionUID = 42L;
    ReservationTile[][] data;

    private void evaluateModel(int[][] input) {
        data = new ReservationTile[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                data[i][j] = new ReservationTile(input[i][j]);
            }
        }
    }

    public Reservations(int[][] input) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel seatsPanel = new JPanel(new GridLayout(input.length, input[0].length));
        evaluateModel(input);

        //painting:
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                seatsPanel.add(data[i][j]);
            }
        }

        add(seatsPanel, BorderLayout.CENTER);

        JButton outButton = new JButton("Save");
        outButton.addActionListener(e -> {
            System.out.println("TBD");
        });
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(outButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    public static void main(String[] args) {
        new Reservations(
                new int[][]{
                        {1, 0, 2, 0, 1, 1},
                        {1, 0, 2, 0, 1, 1},
                        {1, 0, 2, 0, 1, 1},
                        {1, 2, 2, 0, 2, 1},
                        {1, 2, 2, 2, 2, 1},
                }
        ).setVisible(true);
    }
}

class ReservationTile extends JButton {
    public static final int FREE = 0;
    public static final int UNAVAILABLE = 1;
    public static final int OCCUPIED = 2;
    public static final int SELECTED = 3;
    int status;

    public ReservationTile(int status) {
        this.status = status;
        this.setText("");
        this.setFocusable(false);

        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        repaintStatus();
        addActionListener(e -> {
            if (this.status == FREE) {
                setStatus(SELECTED);
                repaintStatus();
            } else if (this.status == SELECTED) {
                setStatus(FREE);
                repaintStatus();
            }
        });
    }

    public void setStatus(int status) {
        System.out.println("Did");
        this.status = status;
        System.out.println(this.status);
    }

    void repaintStatus() {
        switch (status) {
            case FREE -> setBackground(Color.white);
            case UNAVAILABLE -> setBackground(Color.darkGray);
            case OCCUPIED -> setBackground(Color.red);
            case SELECTED -> setBackground(Color.blue);
        }
    }
}