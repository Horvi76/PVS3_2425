package gui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableDemo extends JFrame {

    public TableDemo() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);

        String[] columns = new String[]{"Col 1", "Col 2", "Col 3", "Col 4", "Col 5"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
//        table.setSelectionBackground(Color.cyan);
////        table.setEnabled(false);
//        table.setForeground(Color.RED);
//        table.setBackground(Color.YELLOW);
        table.setFont(new Font("Consolas", Font.BOLD, 16));

        table.setRowHeight(34);
        table.setRowMargin(15);

        JScrollPane sp = new JScrollPane(table);


        model.addRow(new String[]{"A", "B", "C", "D", "E"});
        model.addRow(new String[]{"I", "II", "III", "IV", "V"});
        model.setRowCount(0);//smaze radky

        for (int i = 0; i < 100; i++) {
            model.addRow(new String[]{"I", "II", "III", "IV", "V"});
        }
        this.add(sp);
        pack();
    }

    public static void main(String[] args) {

        FlatDarkLaf.setup();
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        new TableDemo().setVisible(true);
    }
}
