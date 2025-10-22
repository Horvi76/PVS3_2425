package gui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class ProgressDemo extends JFrame {

    JProgressBar bar;

    public ProgressDemo(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(760, 420);
        setLayout(null);


        bar = new JProgressBar();
        bar.setFont(new Font("MV Boli", Font.BOLD, 32));
        bar.setForeground(Color.green);
        bar.setBackground(Color.black);
        bar.setStringPainted(true);
        bar.setBounds(0,0,760,210);
        bar.setValue(0);
        this.add(bar);

//        fill();
    }

    void fill(){
        int counter = 0;
        while (counter <= 100){
            bar.setValue(counter);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter+=1;
        }

        bar.setString("Done!");
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
//        FlatLightLaf.setup();
//        UIManager.setLookAndFeel(new FlatLightLaf());
        ProgressDemo demo = new ProgressDemo();
        demo.setVisible(true);
        demo.fill();
    }
}
