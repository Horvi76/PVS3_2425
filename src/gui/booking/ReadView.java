package gui.booking;

import javax.swing.*;
import java.awt.*;

public class ReadView extends JFrame {

    ReadView(Vacation toDisplay){
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
        JLabel nameInfo = new JLabel(toDisplay.applicant);
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        nameInfo.setFont(new Font("Consolas", Font.PLAIN, 14));
        formPanel.add(nameLabel);
        formPanel.add(nameInfo);

        JLabel phoneLabel = new JLabel("Phone Number:");
        JLabel phoneInfo = new JLabel(toDisplay.phoneNum);
        phoneInfo.setFont(new Font("Consolas", Font.PLAIN, 14));
        phoneLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        formPanel.add(phoneLabel);
        formPanel.add(phoneInfo);

        JLabel discountLabel = new JLabel("Student Discount:");
        JLabel discountInfo = new JLabel(toDisplay.discount ? "Yes" : "No");
        discountLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        discountInfo.setFont(new Font("Consolas", Font.PLAIN, 14));
        formPanel.add(discountLabel);
        formPanel.add(discountInfo);

        JLabel destinationLabel = new JLabel("Destination: ");
        JLabel destinationInfo = new JLabel(toDisplay.dest.toString());
        destinationLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        formPanel.add(destinationLabel);
        formPanel.add(destinationInfo);


        JLabel daysLabel = new JLabel("Days: ");
        JLabel daysInfo = new JLabel(String.valueOf(toDisplay.days));
        daysLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        daysInfo.setFont(new Font("Consolas", Font.PLAIN, 14));
        formPanel.add(daysLabel);
        formPanel.add(daysInfo);
        //zarvonani doprava
        nameInfo.setHorizontalAlignment(SwingConstants.RIGHT);
        phoneInfo.setHorizontalAlignment(SwingConstants.TRAILING);
        discountInfo.setHorizontalAlignment(SwingConstants.TRAILING);
        destinationInfo.setHorizontalAlignment(SwingConstants.TRAILING);
        daysInfo.setHorizontalAlignment(SwingConstants.TRAILING);

        add(formPanel, BorderLayout.CENTER);
    }
}
