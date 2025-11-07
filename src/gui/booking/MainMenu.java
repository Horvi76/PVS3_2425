package gui.booking;

import javax.print.attribute.standard.Destination;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainMenu extends JFrame {

    static final Font DEAFULT_FONT = new Font("Consolas", Font.BOLD, 18);
    static final Font DEAFULT_BUTTON_FONT = new Font("Consolas", Font.BOLD, 14);
    public static DefaultTableModel model;
    public static ArrayList<Vacation> data = new ArrayList<>();
    public MainMenu() {
        setLocationRelativeTo(null);
        setTitle("Vacation manager");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        //NORTH
        JLabel headerLabel = new JLabel("Manage vacation applications");
        headerLabel.setFont(DEAFULT_FONT);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //CENTER - table only
        String[] headers = {"Name", "Phone num.", "Destination", "Days", "Discounted"};
        model = new DefaultTableModel(headers, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        //testing
        Vacation tester = new Vacation("Tester", "111222879", 2, 31, false);
        data.add(tester);
        model.addRow(tester.getTableRow());
        //south
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton inputButton = new JButton("New application");
        inputButton.addActionListener(e -> {
            new Booking().setVisible(true);
        });
        JButton saveButton = new JButton("Save");
        buttonsPanel.add(inputButton);
        buttonsPanel.add(saveButton);

        //add sekce
        //north
        add(headerLabel, BorderLayout.NORTH);
        //center
        add(scrollPane, BorderLayout.CENTER);
        //south
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new MainMenu().setVisible(true);
    }
}
