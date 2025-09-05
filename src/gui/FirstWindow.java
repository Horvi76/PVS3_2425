package gui;

import javax.swing.*;

public class FirstWindow extends JFrame {

    public FirstWindow(){
        this.setSize(450, 200);
        this.setTitle("First window example");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public static void main(String[] args) {
//        FirstWindow fw = new FirstWindow();
//        fw.setVisible(true);
        new FirstWindow().setVisible(true);
    }
}
