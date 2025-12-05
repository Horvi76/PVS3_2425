package gui;

import javax.swing.*;
import java.awt.*;

public class Sliding extends JFrame {
    public Sliding(){
        setSize(420,420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        JSlider slider = new JSlider(-100,100, 21);
        slider.setPreferredSize(new Dimension(400, 200));

        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);

        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(50);

        slider.setPaintLabels(true);
        slider.setFont(new Font("MV Boli", Font.PLAIN, 15));

        slider.setForeground(Color.blue);
//        slider.setBackground(Color.green);

        slider.setOrientation(SwingConstants.VERTICAL);
        label.setText("°C = " + slider.getValue());
        label.setFont(new Font("MV Boli", Font.BOLD, 24));

        slider.addChangeListener(l -> {
            label.setText("°C = " + slider.getValue());
        });
        panel.add(slider);
        panel.add(label);
        this.add(panel);
    }

    public static void main(String[] args) {
        new Sliding().setVisible(true);
    }
}
