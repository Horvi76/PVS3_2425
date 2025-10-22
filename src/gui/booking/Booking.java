package gui.booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Booking extends JFrame {
    public Booking() {
        setTitle("Booking Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("GetAway", JLabel.CENTER);
        headerLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        add(headerLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField nameField = new JTextField();
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField phoneField = new JTextField();
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        JLabel discountLabel = new JLabel("Student Discount:");
        discountLabel.setFont(new Font("Consolas", Font.PLAIN, 14));

        JPanel discountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JCheckBox discountCheckBox = new JCheckBox();
        discountPanel.add(discountCheckBox);

        formPanel.add(discountLabel);
        formPanel.add(discountPanel);

        JLabel optionsLabel = new JLabel("Choose Your Destination:");
        optionsLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton beachOption = new JRadioButton("Beach");
        beachOption.setFont(new Font("Consolas", Font.PLAIN, 12));
        JRadioButton mountainOption = new JRadioButton("Mountains");
        mountainOption.setFont(new Font("Consolas", Font.PLAIN, 12));
        JRadioButton cityOption = new JRadioButton("City");
        cityOption.setFont(new Font("Consolas", Font.PLAIN, 12));
        ButtonGroup optionsGroup = new ButtonGroup();
        optionsGroup.add(beachOption);
        optionsGroup.add(mountainOption);
        optionsGroup.add(cityOption);
        optionsPanel.add(beachOption);
        optionsPanel.add(mountainOption);
        optionsPanel.add(cityOption);
        formPanel.add(optionsLabel);
        formPanel.add(optionsPanel);

        JLabel daysLabel = new JLabel("Days:");
        daysLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        JSlider daysSlider = new JSlider(5, 105);
        daysSlider.setMajorTickSpacing(25);
        daysSlider.setMinorTickSpacing(5);
        daysSlider.setPaintTicks(true);
        daysSlider.setPaintLabels(true);
        daysSlider.setFont(new Font("Consolas", Font.PLAIN, 12));
        formPanel.add(daysLabel);
        formPanel.add(daysSlider);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Consolas", Font.BOLD, 14));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Consolas", Font.BOLD, 14));

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                phoneField.setText("");
                discountCheckBox.setSelected(false);
                optionsGroup.clearSelection();
                daysSlider.setValue(0);
            }
        });
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        new Booking().setVisible(true);
    }
}
