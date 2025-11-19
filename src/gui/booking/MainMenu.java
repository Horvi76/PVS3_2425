package gui.booking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MainMenu extends JFrame {

    static final Font DEAFULT_FONT = new Font("Consolas", Font.BOLD, 18);
    static final Font DEAFULT_BUTTON_FONT = new Font("Consolas", Font.BOLD, 14);
    public static final String LOAD_FILE_PATH = "VacSave.ser";
    public static JTable table;
    public static DefaultTableModel model;


    public static ArrayList<Vacation> data;

    /**
     * Refresh tabulky pro GUI ucely.
     * - deserializace souboru
     * - nacist data do
     */
    void displayData() {
        if (new File(LOAD_FILE_PATH).isFile()) {
            try {
                ObjectInputStream loader = new ObjectInputStream(new FileInputStream(LOAD_FILE_PATH));
                data = (ArrayList<Vacation>) loader.readObject();
                loader.close();
                //vycisti tabulku
                model.setRowCount(0);
                for (Vacation v : data){
                    model.addRow(v.getTableRow());
                }
            } catch (IOException e) {
                System.out.println("Trouble loading the initial save file: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("No 'Vacation' class found, check your files: " + e.getMessage());
            }
        } else {
            data = new ArrayList<>();
            System.out.println("No initial data file loaded.");
        }
    }

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
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        //south
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton inputButton = new JButton("New application");
        inputButton.addActionListener(e -> {
            new Booking(null).setVisible(true);
        });
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            //serializace ven:
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LOAD_FILE_PATH));
                oos.writeObject(data);
                oos.close();
                JOptionPane.showMessageDialog(this, "Save file created", "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Trouble writing out the file :(");
            }

        });
        JButton detailButton = new JButton("Detail");
        detailButton.addActionListener(e -> {
            new ReadView(data.get(table.getSelectedRow())).setVisible(true);
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(a -> {
            int input = JOptionPane.showConfirmDialog(null, "Do you wish to delete selected record?", "Delete record", JOptionPane.YES_NO_OPTION);
            if (input == JOptionPane.YES_OPTION){
                data.remove(table.getSelectedRow());
                model.removeRow(table.getSelectedRow());
                //uz neni nutne:
//                table.remove(table.getSelectedRow());
            }
        });
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            if (table.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "No row is selected for editing", "Error", JOptionPane.ERROR_MESSAGE);
            }
            new Booking(data.get(table.getSelectedRow())).setVisible(true);
        });
        editButton.setFont(DEAFULT_BUTTON_FONT);
        deleteButton.setFont(DEAFULT_BUTTON_FONT);
        saveButton.setFont(DEAFULT_BUTTON_FONT);
        inputButton.setFont(DEAFULT_BUTTON_FONT);
        detailButton.setFont(DEAFULT_BUTTON_FONT);
        buttonsPanel.add(detailButton);
        buttonsPanel.add(inputButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(saveButton);
        
        //add sekce
        //north
        add(headerLabel, BorderLayout.NORTH);
        //center
        add(scrollPane, BorderLayout.CENTER);
        //south
        add(buttonsPanel, BorderLayout.SOUTH);

        displayData();
    }


    public static void main(String[] args) {
        new MainMenu().setVisible(true);
    }
}
