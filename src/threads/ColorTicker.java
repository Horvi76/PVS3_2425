package threads;

import javax.swing.*;
import java.awt.*;

public class ColorTicker extends JFrame {

    JTextField[] colors;
    JLabel colorDisplay;

    public ColorTicker(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Color switching");
        setLocationRelativeTo(null);

        setLayout(new FlowLayout());
        colors = new JTextField[3];
        for (int i = 0; i < 3; i++) {
            colors[i] = new JTextField(" ");
            colors[i].setPreferredSize(new Dimension(100, 70));
            colors[i].setFont(new Font("Consolas", Font.PLAIN, 28));
            colors[i].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colors[i]);
        }

        colorDisplay = new JLabel(" ");
        colorDisplay.setOpaque(true);
        colorDisplay.setPreferredSize(new Dimension(200, 140));
        colorDisplay.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(colorDisplay);

        JButton run = new JButton("Start");
        run.addActionListener(e -> {
            Thread ticker = new Thread(this::tickColor);
            ticker.start();
        });
        add(run);
        pack();
    }

    void tickColor() {
        for (;;){
            setColor();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Interrupted, exiting...");
                System.exit(1);
            }
        }
    }

    void setColor(){
        colorDisplay.setBackground(new Color(
                Integer.parseInt(colors[0].getText()),
                Integer.parseInt(colors[1].getText()),
                Integer.parseInt(colors[2].getText())
        ));
    }

    public static void main(String[] args) {
        new ColorTicker().setVisible(true);
    }
}
