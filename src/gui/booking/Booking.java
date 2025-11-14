package gui.booking;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Booking extends JFrame {

    JRadioButton beachOption, cityOption, mountainsOption;

    public Booking(Vacation vacation) {
        setTitle("Booking Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        beachOption = new JRadioButton("Beach");
        beachOption.setFont(new Font("Consolas", Font.PLAIN, 12));
        mountainsOption = new JRadioButton("Mountains");
        mountainsOption.setFont(new Font("Consolas", Font.PLAIN, 12));
        cityOption = new JRadioButton("City");
        cityOption.setSelected(true);
        cityOption.setFont(new Font("Consolas", Font.PLAIN, 12));
        ButtonGroup optionsGroup = new ButtonGroup();
        optionsGroup.add(beachOption);
        optionsGroup.add(mountainsOption);
        optionsGroup.add(cityOption);
        optionsPanel.add(beachOption);
        optionsPanel.add(mountainsOption);
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
        if (vacation != null) {
            //data load
            nameField.setText(vacation.applicant);
            phoneField.setText(vacation.phoneNum);
            discountCheckBox.setSelected(vacation.discount);
            switch (vacation.dest) {
                case CITY -> cityOption.setSelected(true);
                case MOUNTAINS -> mountainsOption.setSelected(true);
                case BEACH -> beachOption.setSelected(true);
            }
            daysSlider.setValue(vacation.days);
        }
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Consolas", Font.BOLD, 14));
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Consolas", Font.BOLD, 14));
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            StringBuilder errors = new StringBuilder();
            if (nameField.getText().isEmpty()) {
                errors.append("- Name field cannot be empty.\n");
            }

            if (phoneField.getText().length() != 9) {
                errors.append("- Phone number must be 9 digits long.\n");
            } else {
                for (int i = 0; i < phoneField.getText().length(); i++) {
                    if (!Character.isDigit(phoneField.getText().charAt(i))) {
                        errors.append("- Only digits allowed for phone number field.\n");
                        break;
                    }
                }
            }
            if (discountCheckBox.isSelected() && !cityOption.isSelected()) {
                errors.append("- You can only submit reservation for a city using student's discount.\n");
            }

            if (beachOption.isSelected() && daysSlider.getValue() > 60) {
                errors.append("- Beach options allow only 60 days reservation.\n");
            }

            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Following errors occurred:\n" + errors, "Errors", JOptionPane.ERROR_MESSAGE);
            } else {
                if (vacation != null) {
                    //propis edity - data
                    vacation.dest = Destinations.getDestByCode(getDestinationCode());
                    vacation.applicant = nameField.getText();
                    vacation.phoneNum = phoneField.getText();
                    vacation.discount = discountCheckBox.isSelected();
                    vacation.days = daysSlider.getValue();

                    //propsat edity - table
                    MainMenu.model.removeRow(MainMenu.table.getSelectedRow());
                    MainMenu.model.addRow(vacation.getTableRow());
                } else {
                    Vacation v = new Vacation(
                            nameField.getText(),
                            phoneField.getText(),
                            getDestinationCode(),
                            daysSlider.getValue(),
                            discountCheckBox.isSelected()
                    );
                    MainMenu.model.addRow(v.getTableRow());
                    MainMenu.data.add(v);
                }
                JOptionPane.showMessageDialog(null, "Ok", "Info", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        clearButton.addActionListener(new ActionListener() {
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

    int getDestinationCode() {
        if (beachOption.isSelected()) {
            return 0;
        } else if (mountainsOption.isSelected()) {
            return 2;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        new Booking(null).setVisible(true);
    }
}
