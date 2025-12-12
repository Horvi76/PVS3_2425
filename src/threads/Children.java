package threads;

import javax.swing.*;
import java.time.LocalDateTime;

public class Children {
    public static void main(String[] args) {
        Thread timer = new Thread(() -> {
           while (true){
               System.out.println(LocalDateTime.now());
               try {
                   Thread.sleep(3000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });
        System.out.println("Je to daemon?" + timer.isDaemon());

        timer.setDaemon(true);
        System.out.println("Je to daemon?" + timer.isDaemon());
        timer.start();
        String input = JOptionPane.showInputDialog("Neco zadej...");
        System.out.println("Zadal jsi " + input);
//        System.exit(1);
    }
}
