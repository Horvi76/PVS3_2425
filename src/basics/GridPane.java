package basics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridPane extends JFrame {

    public GridPane(int dim) throws HeadlessException {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(dim, dim));
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if ((i+j)%2 == 1){
                    this.add(new Piece(new Color(0xff0000)));
                } else {
                    this.add(new Piece(Color.cyan));
                }
            }
        }


        this.setSize(450, 450);
//        this.pack();
    }

    public static void main(String[] args) {
        new GridPane(10).setVisible(true);
    }
}
class Piece extends JLabel{

    static Color HOVER_COLOR = Color.orange;
    static Color CLICK_COLOR = Color.magenta;
    boolean alreadyClicked = false;
    public Piece(Color color){
        this.setText(" ");
        this.setFont(new Font("Consolas", Font.BOLD, 20));
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.setOpaque(true);
        this.setBackground(color);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setBackground(CLICK_COLOR);
                alreadyClicked = true;
            }

            @Override
            public void mousePressed(MouseEvent e) {
//                setBackground(CLICK_COLOR);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
               if (!alreadyClicked)
                  setBackground(HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!alreadyClicked)
                    setBackground(color);
            }
        });
    }
}
