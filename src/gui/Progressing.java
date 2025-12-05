package gui;

import javax.swing.*;
import java.awt.*;

public class Progressing extends JFrame {

    JProgressBar bar;

    public Progressing(){
        setSize(420, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        bar = new JProgressBar();
        bar.setFont(new Font("Consolas", Font.BOLD, 25));
        bar.setForeground(Color.magenta);
        bar.setBackground(Color.black);
        bar.setBounds(0,0, getWidth(), 50);
        bar.setStringPainted(true);
        bar.setValue(0);

        add(bar);
    }

    public void fill(){
        int counter = 0;
        while (counter <= 100){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter++;
            bar.setValue(counter);
        }
        bar.setString("Done!");
    }

    public static void main(String[] args) {
        Progressing demo = new Progressing();
        demo.setVisible(true);
        demo.fill();
    }
}
