package threads.coords;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CoordsRead {
    static List<Coordinates> all = new ArrayList<>();

    static void processOneFile(String fileName) throws IOException {
        String line;
        String[] attributes;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {
            attributes = line.split(",");
            all.add(new Coordinates(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1])));
        }
        br.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //single thread pristup
        String basename = "num";
        int amount = 50;
        //vse singlethread:
//        for (int i = 0; i < amount; i++) {
//            processOneFile(basename + i + ".txt");
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < amount; i++) {
            int fileIndex = i;
            executorService.submit(() -> {
                try {
                    processOneFile(basename + fileIndex + ".txt");
                } catch (IOException e) {
                    System.out.println("Trouble multithreading :(");
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println(all.size());
    }
}

class Coordinates {
    int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
