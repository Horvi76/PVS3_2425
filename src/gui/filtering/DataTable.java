package gui.filtering;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataTable extends JFrame {
    static ArrayList<Record> data;
    static DefaultTableModel model;

    public DataTable(String title) throws IOException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        data = new ArrayList<>();
        this.setLayout(new BorderLayout());
        SortMenu menu = new SortMenu();
        this.add(menu, BorderLayout.NORTH);

        String[] column = {"Name","Release year","Rating","Duration"};
        model = new DefaultTableModel(column,0);
        //nacteni a parse
        List<String> lines = Files.readAllLines(Paths.get("Movies.txt"));
        String[] vals;
        //nacte text a zaparsuje
        for (String line : lines){
            vals = line.split(";");
            data.add(new Record(
                    vals[0],
                    Integer.parseInt(vals[1]),
                    Integer.parseInt(vals[3]),
                    Double.parseDouble(vals[2])
            ));
        }

        JTable table = new JTable(model);
        table.setEnabled(false);
        JScrollPane sp = new JScrollPane(table);

        loadDataToTable();

        this.add(sp, BorderLayout.CENTER);
        this.setSize(500, 600);
        this.setResizable(false);
    }

    static void loadDataToTable(){
        for (Record r : data){
            model.addRow(r.returnAsTableRow());
        }
    }

    static void clearTable(){
        model.setRowCount(0);
    }

    public static void main(String[] args) throws IOException{
        DataTable table = new DataTable("Vizualizace dat");
        table.setVisible(true);
    }
}
