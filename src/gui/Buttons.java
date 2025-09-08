package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Buttons extends JFrame implements ActionListener{
    JButton button;
    JLabel label;
    public Buttons(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        button = new JButton("Hide");
        button.setBackground(Color.magenta);
        button.setForeground(Color.cyan);

        button.setFont(new Font("Consolas", Font.BOLD,36));

//        button.setEnabled(true);

        //xy souradnice pro umisteni
        this.setLayout(null);
        button.setBounds(0,0,250,100);
        button.addActionListener(e -> {
            if (button.getText().equals("Hide")){
                button.setText("Show");
                label.setBackground(label.getForeground());
            } else {
                button.setText("Hide");
                label.setBackground(Color.white);
            }
        });


        JButton anotherButton = new JButton("Do");
        anotherButton.setFont(new Font("Consolas", Font.BOLD,36));
        anotherButton.setBackground(Color.green);
        anotherButton.setForeground(Color.red);
        anotherButton.setBounds(250,0, 250, 100);
        anotherButton.setFocusable(false);
//        anotherButton.addActionListener(this);
//        anotherButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Second button was pressed");
//            }
//        });
//
//        anotherButton.addActionListener(e -> {
//            System.out.println("Zmacknuto Bylo");
//            System.out.println("Tlacitko: " + e.getSource());
//        });

        label = new JLabel("text");
        label.setBounds(0, 100, 500, 400);
        label.setBackground(Color.white);
        label.setOpaque(true);

        label.setFont(new Font("Consolas", Font.BOLD, 48));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);


        anotherButton.addActionListener(e -> {
            Random r = new Random();
            Color c = label.getBackground();
            do {
                label.setBackground(new Color(
                        r.nextInt(256),
                        r.nextInt(256),
                        r.nextInt(256)
                ));
            } while (c.equals(label.getBackground()));
        });

        anotherButton.setEnabled(false);
        this.add(label);
        this.add(button);
        this.add(anotherButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button)
            System.out.println("First button was pressed");
//        System.out.println("Some button was pressed");
    }

    public static void main(String[] args) {
        new Buttons().setVisible(true);
    }
}
