package threads;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ControlledAppend {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("outAppend.txt"));
        pw.println("Lines below are appended:");
        pw.close();

        PrintWriter appending = new PrintWriter(new FileWriter("outAppend.txt", true));
        System.out.println("Zadavej vstupy do souboru, zakoncis napsanim 'konec'");
        String input;

        Thread keepAlive = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(3000);
                    if (new File("outAppend.txt").exists()){
                        System.out.println("Soubor OK");
                    } else {
                        System.out.println("Soubor uz neni OK");
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        keepAlive.setDaemon(true);
        keepAlive.start();
        while (!(input = JOptionPane.showInputDialog("Append: ")).equals("konec") && keepAlive.isAlive()){
            appending.println(input);
            appending.flush();
        }
        appending.close();
    }

}
