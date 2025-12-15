package exams;

import javax.swing.*;

public class ThreadsCountdown {
    static String input = "";
    static final String PASSWORD = "open";
    public static void main(String[] args) throws InterruptedException {
        Thread inputThread = new Thread(() -> {
            input = JOptionPane.showInputDialog("Zadej heslo");
        });
        inputThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(10 - i);
            Thread.sleep(1000);
            if (!inputThread.isAlive()){
                if (input.equals(PASSWORD)){
                    System.out.println("All good :)");
                } else {
                    System.out.println("Spatne heslo :(");
                }
                break;
            }
        }
        System.out.println("Done.  Heslo OK?" + input.equals(PASSWORD));
        if (inputThread.isAlive()){
            inputThread.interrupt();
        }
    }
}
