package exams;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BloodTest extends JFrame {

    public static ArrayList<Donation> donations;

    public BloodTest(){
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        donations = new ArrayList<>();

        JButton listButton = new JButton("List Donations");
        listButton.addActionListener(e -> {
            for(Donation d : donations){
                System.out.println(d);
            }
        });

        JButton reserveButton = new JButton("New reservation");
        reserveButton.addActionListener(e -> new DonationForm().setVisible(true));
        donations.add(new Donation("Tester", "Male", false, LocalDate.now().minusMonths(2)));

        add(listButton);
        add(reserveButton);
        pack();
    }


    public static void main(String[] args) {
        BloodTest bt = new BloodTest();
        bt.setVisible(true);
    }
}

class Donation {
    String name;
    String gender;
    boolean firstTimer;
    LocalDate date;

    public Donation(String name, String gender, boolean firstTimer, LocalDate date) {
        this.name = name;
        this.gender = gender;
        this.firstTimer = firstTimer;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isFirstTimer() {
        return firstTimer;
    }

    public void setFirstTimer(boolean firstTimer) {
        this.firstTimer = firstTimer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", firstTimer=" + firstTimer +
                ", date=" + date +
                '}';
    }
}

class DonationForm extends JFrame {

    //Pravidla:
    //1. Date/Name nesmi byt prazdny
    //Pokud nedaruje prvne, kontroluje se:
    //Jestli nekdo s tim jmenem uz v listu neni a pokud ano, kontroluje se:
    //pokud zena: alespon 120 dni od mezi darovanim
    //pokud muz: alespon 90 dni mezi darovanim

    private final JTextField nameField = new JTextField(20);
    private final JRadioButton maleRadio = new JRadioButton("Male");
    private final JRadioButton femaleRadio = new JRadioButton("Female");
    private final JCheckBox firstTimeCheckBox = new JCheckBox("First-time donor");
    private final JTextField dateField = new JTextField(10);



    DonationForm(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //obecny formular
        setLayout(new BorderLayout(10, 10));

        //vnitrek formu
        JPanel formPanel = new JPanel(new GridLayout(4,2,5,5));
        //Name
        formPanel.add(new JLabel("Full name:"));
        formPanel.add(nameField);
        // Gender
        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup group = new ButtonGroup();
        group.add(maleRadio);
        group.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(genderPanel);
        // First-time
        formPanel.add(new JLabel("First-time donor:"));
        formPanel.add(firstTimeCheckBox);
        // Date
        formPanel.add(new JLabel("Date (dd-MM-yyyy):"));
        formPanel.add(dateField);
        add(formPanel, BorderLayout.CENTER);

        //buttons na pridani/cancel
        // Buttons
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        okButton.addActionListener(e -> {
            if (tryReservation()){
                JOptionPane.showMessageDialog(null, "Ok", "Info", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        cancelButton.addActionListener(e -> dispose());

        add(buttonsPanel, BorderLayout.SOUTH);
        pack();
    }

    boolean tryReservation(){
        Donation donation = getDonation();
        if(donation == null) return false;

        List<Donation> filtered = BloodTest.donations.stream()
                .filter(d -> d.getName().equals(donation.getName()))
                .toList();
        if (filtered.isEmpty()){
            BloodTest.donations.add(donation);
            return true;
        }

        for (Donation checkAgainst : filtered){
            long daysBetween = ChronoUnit.DAYS.between(checkAgainst.getDate(), donation.getDate());
            if (donation.getGender().equals("Female") && daysBetween < 120){
                JOptionPane.showMessageDialog(null, "Zena musi mit 120 dni mezi darovanim, u vas je " + daysBetween, "Errors", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (donation.getGender().equals("Male") && daysBetween < 90){
                JOptionPane.showMessageDialog(null, "Muz musi mit 90 dni mezi darovanim, u vas je " + daysBetween, "Errors", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        BloodTest.donations.add(donation);
        return true;
    }

    Donation getDonation() {
        if (nameField.getText().isEmpty() || dateField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Date and name fields cannot be empty", "Errors", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        LocalDate date = parseDate(dateField.getText());
        if (date == null)
            return null;
        String gender = maleRadio.isSelected() ? "Male" : "Female";
        return new Donation(nameField.getText(), gender, firstTimeCheckBox.isSelected(), parseDate(dateField.getText()));
    }

    public static LocalDate parseDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(text, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Ma byt: dd-MM-yyyy, je: " + text, "Errors", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
