package gui.filtering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class SortMenu extends JPanel {

    /**
     * Nejdulezitejsi!
     * Tomuto tlacitku pridat posluchac, zadny defaultne nema
     */
    public JButton outputButton;

    JRadioButton name, release, rating, duration;

    public SortMenu() {
        this.setLayout(new FlowLayout());
        name = new JRadioButton("Name");
        release = new JRadioButton("Release");
        rating = new JRadioButton("Rating");
        duration = new JRadioButton("Duration");

        ButtonGroup sortButtons = new ButtonGroup();
        sortButtons.add(name);
        sortButtons.add(release);
        sortButtons.add(rating);
        sortButtons.add(duration);

        JLabel label = new JLabel("Sort by: ");

        outputButton = new JButton("Sort!");

        outputButton.addActionListener(e -> {

            if (name.isSelected()){
                DataTable.data.sort((o1, o2) -> o1.name.compareTo(o2.name));
            }
            if (duration.isSelected()){
                DataTable.data.sort((o1, o2) -> Integer.compare(o1.duration, o2.duration));
            }
            if (rating.isSelected()){
                DataTable.data.sort((o1, o2) -> Double.compare(o1.rating, o2.rating));
            }
            if (release.isSelected()){
                DataTable.data.sort((o1, o2) -> Integer.compare(o1.yearOfRelease, o2.yearOfRelease));

            }
            DataTable.clearTable();
            DataTable.loadDataToTable();
            System.out.println(DataTable.data);
        });

        this.add(label);
        this.add(name);
        this.add(release);
        this.add(rating);
        this.add(duration);

        this.add(outputButton);
    }

}
