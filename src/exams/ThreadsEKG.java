package exams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ThreadsEKG {
    public static ArrayList<Integer> beats = new ArrayList<>();
    static final int SIM_DURATION = 10;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Simulace zacina: ");
        Thread dataGen = new Thread(() -> {
            int totalDuration = (SIM_DURATION * 1000) / 250;
            Random r = new Random();
            for (int i = 0; i < totalDuration; i++) {
                beats.add(r.nextInt(40, 80));
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    System.out.println("Datagen died :(");
                }
            }
        });

        Thread stats = new Thread(() -> {
            for (; ;) { //while(true)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Stats died :(");
                }
                System.out.println("min: " + Collections.min(beats) +
                        ", max: " + Collections.max(beats) +
                        ", avg: " + beats.stream().mapToInt(Integer::intValue).sum() / beats.size());
            }
        });
        stats.setDaemon(true);
        dataGen.start();
        stats.start();

        //tady pocka main...
        dataGen.join();
        System.out.println("Simulace ukoncena :)");
    }
}
