package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ScreenSaver extends JFrame {
    public ScreenSaver(){
        Canvas panel = new Canvas();
        this.add(panel);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        new ScreenSaver().setVisible(true);
    }

}
class Canvas extends JPanel implements ActionListener {
    final int CANVAS_WIDTH = 1200;
    final int CANVAS_HEIGHT = 1200;
    Image bouncing;
    int x = 0;
    int y = 0;
    int xVelocity = 5;
    int yVelocity = 2;
    Random random = new Random(5);


    Canvas(){
        this.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        this.setBackground(Color.black);

        bouncing = new ImageIcon("dvd.png").getImage();
        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bouncing, x,y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(x >= CANVAS_WIDTH - bouncing.getWidth(null) || x < 0){
           xVelocity *= -1;
       }
        x += xVelocity;

        if (y >= CANVAS_HEIGHT - bouncing.getHeight(null) || y<0){
            yVelocity*=-1;
        }
        y += yVelocity;

        repaint();
    }
}
