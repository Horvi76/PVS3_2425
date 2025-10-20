package gui.browser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardMenu extends JFrame {
    JLabel fullName, salary, shoeSize;
    ImageIcon avatar;
    JPanel lowerMenu;
    JButton next, previous;
    int cursor;

    CardMenu(){
        cursor = 0;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,400);
        this.setResizable(false);

        this.setLayout(new BorderLayout());
        lowerMenu = new JPanel();
        lowerMenu.setLayout(new FlowLayout());


        fullName = new JLabel();
        salary = new JLabel();
        shoeSize = new JLabel();

        fullName.setFont(new Font("MV Boli", Font.BOLD, 18));
        salary.setFont(new Font("MV Boli", Font.BOLD, 18));
        shoeSize.setFont(new Font("MV Boli", Font.BOLD, 18));

//        avatar = MainMenu.personData.get(0).avatar;//zaciname na prvnim
        avatar = new ImageIcon(MainMenu.personData.get(cursor).avatar.getImage().getScaledInstance(500,450, Image.SCALE_DEFAULT));
        fullName.setText("Full name: " + MainMenu.personData.get(cursor).name + " " + MainMenu.personData.get(cursor).surname);

        fullName.setIcon(avatar);
        fullName.setHorizontalTextPosition(JLabel.CENTER);
        fullName.setVerticalTextPosition(JLabel.TOP);
        fullName.setIconTextGap(5);
        fullName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        this.add(fullName, BorderLayout.CENTER);

        shoeSize.setText("Shoe size: " + MainMenu.personData.get(cursor).shoeSize);
        salary.setText("Salary: " + MainMenu.personData.get(cursor).salary);
        previous = new JButton("Previous");
        next = new JButton("Next");
        lowerMenu.add(previous);
        previous.addActionListener(e -> {
            cursor--;
            reload();
        });
        lowerMenu.add(shoeSize);
        lowerMenu.add(salary);
        next.addActionListener(e -> {
            cursor++;
            reload();
        });

        lowerMenu.add(next);

        this.add(lowerMenu, BorderLayout.SOUTH);
        this.pack();
    }


    void reload(){
        avatar = new ImageIcon(MainMenu.personData.get(cursor).avatar.getImage().getScaledInstance(500,450, Image.SCALE_DEFAULT));
        fullName.setText("Full name: " + MainMenu.personData.get(cursor).name + " " + MainMenu.personData.get(cursor).surname);
        fullName.setIcon(avatar);

        shoeSize.setText("Shoe size: " + MainMenu.personData.get(cursor).shoeSize);
        salary.setText("Salary: " + MainMenu.personData.get(cursor).salary);
    }

}
