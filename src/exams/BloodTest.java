package exams;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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

    Donation getDonation() {
        return null;
    }

    public static LocalDate parseDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(text, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Ma byt: dd-MM-yyyy, je: " + text);
        }
    }

}
