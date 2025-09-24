package basics;

import javax.swing.*;
import java.awt.*;

public class OwnComponent extends JFrame {

    public OwnComponent(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(400, 400);


        this.setLayout(new GridLayout(2,5));

        for (int i = 0; i < 10; i++) {
            this.add(new HeaderTile("nadpis", i));
        }

    }

    public static void main(String[] args) {
        new OwnComponent().setVisible(true);
    }
}
class HeaderTile extends JPanel{
    String header;
    int num;

    public HeaderTile(String header, int num) {
        this.header = header;
        this.num = num;


        JLabel headerLabel = new JLabel(header);
        JLabel numLabel = new JLabel(String.valueOf(num));

        headerLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        numLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numLabel.setBackground(Color.yellow);
        numLabel.setOpaque(true);

        headerLabel.setBackground(Color.white);
        headerLabel.setOpaque(true);

        this.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        this.setLayout(new GridLayout(2,1));

        this.add(headerLabel);
        this.add(numLabel);

    }


}