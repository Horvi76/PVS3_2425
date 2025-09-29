package gui.filtering;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DataTable extends JFrame {
    ArrayList<Record> data;
    DefaultTableModel model;

    public DataTable(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        data = new ArrayList<>();
        this.setLayout(new BorderLayout());
        SortMenu menu = new SortMenu();
        this.add(menu, BorderLayout.NORTH);

        String[] column = {"Name","Release year","Rating","Duration"};
        model = new DefaultTableModel(column,0);
        JTable table = new JTable(model);
        table.setEnabled(false);
        JScrollPane sp = new JScrollPane(table);

        this.add(sp, BorderLayout.CENTER);
        this.setSize(500, 600);
        this.setResizable(false);
    }

    void clearTable(){
        model.setRowCount(0);
    }

    public static void main(String[] args) {
        DataTable table = new DataTable("Vizualizace dat");
        table.setVisible(true);
    }
}
