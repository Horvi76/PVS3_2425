package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldOptions extends JFrame implements ActionListener {
    JRadioButton enButton, disButton;
    JTextField tf;
    public TextFieldOptions(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);
        this.setSize(700, 200);
        tf = new JTextField("text");
        tf.setFont(new Font("Arial", Font.PLAIN, 48));
//        tf.setSize(200, 100);
        tf.setPreferredSize(new Dimension(350, 100));
        tf.setBackground(Color.yellow);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
//        tf.getText()
//        tf.setText();
//        this.pack();

        enButton = new JRadioButton("Enable");
        enButton.setFont(new Font("Arial", Font.PLAIN, 28));
        enButton.setBackground(Color.blue);
        enButton.setForeground(Color.magenta);

//        enButton.setEnabled(false);
//        enButton.isEnabled();

        enButton.setSelected(true);
        disButton = new JRadioButton("Disable");
        disButton.setFont(new Font("Arial", Font.PLAIN, 28));

        ButtonGroup group = new ButtonGroup();
        group.add(enButton);
        group.add(disButton);

        enButton.addActionListener(this);
        disButton.addActionListener(this);

        JCheckBox ch = new JCheckBox("Text");
        this.add(ch);
//        ch.isSelected();

        this.add(tf);
        this.add(enButton);
        this.add(disButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enButton || e.getSource() == disButton){
            if (enButton.isSelected())
                tf.setEnabled(true);
            else
                tf.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new TextFieldOptions().setVisible(true);
    }
}
