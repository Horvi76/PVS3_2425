package gui;

import javax.swing.*;
import java.awt.*;

public class SliderDemo extends JFrame {

    public SliderDemo(){
        this.setSize(700,400);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JSlider slider = new JSlider(0, 200);
        slider.setPreferredSize(new Dimension(400, 200));
        JLabel valueLabel = new JLabel("TMP");

        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(25);
        slider.setValue(100);
        slider.setPaintLabels(true);
        slider.setOrientation(SwingConstants.VERTICAL);
        slider.addChangeListener(e -> {
            valueLabel.setText(slider.getValue()+" %");
        });
        this.add(slider);
        this.add(valueLabel);
    }

    public static void main(String[] args) {
        new SliderDemo().setVisible(true);
    }
}
