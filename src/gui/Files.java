package gui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

public class Files{
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        FlatIntelliJLaf.setup();
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        JFileChooser chooser = new JFileChooser();
        int retValue = chooser.showOpenDialog(null);
        System.out.println(retValue);
        System.out.println(retValue == JFileChooser.APPROVE_OPTION);
        System.out.println(retValue == JFileChooser.CANCEL_OPTION);
        System.out.println(retValue == JFileChooser.ERROR_OPTION);
        System.out.println(chooser.getSelectedFile());
    }
}
