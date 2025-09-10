package exceptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Showcase {

    static void createAndPrint(String file) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));

            bw.write("test");

            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printFile(file);
    }

    static void printFile(String file) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : lines)
            System.out.println(line);
    }

    public static void main(String[] args) {
        try {
            int number;
            String alsoNumber;

            Scanner sc = new Scanner(System.in);
            System.out.println("Zadej cislo: ");
            number = sc.nextInt();

            System.out.println("Zadej desetinne cislo: ");
            alsoNumber = Double.toString(sc.nextDouble());

            System.out.println(number);
            System.out.println(alsoNumber);

            int[] arr = {1, 2};
            System.out.println(arr[number]);
            System.out.println("Zadej cislo:");
            int another = sc.nextInt();
            int result = number / another;
            System.out.println(result);
            System.out.println("Happy ending");
        } catch (InputMismatchException e) {
            System.out.println("Nebyl zadan spravny format cisla");
            System.out.println(e.toString());
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Spatny index v poli");
        }
        catch (Exception e){
            System.out.println("Neco se podelalo...");
        }

//        Thread.sleep(1000);

//        printFile("test.txt");
        createAndPrint("test.txt");
    }
}
