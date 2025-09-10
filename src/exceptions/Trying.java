package exceptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Trying {

    public static void main(String[] args) {
        //Vytvor soubor, pridej do nej deset cisel:
        Random r = new Random();
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
            for (int i = 0; i < 10; i++) {
                pw.println(r.nextInt(0, 1000));
            }
            pw.close();
        } catch (IOException e){
            System.out.println("Problem pri psani do souboru...");
        } finally {
            System.out.println("Prace se souborem ukoncena");
        }

        //'try-with-resources', nekdy take 'try-with'
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("alsoOutput.txt")))){
            for (int i = 0; i < 10; i++) {
                pw.println(r.nextInt(0, 1000));
            }
            //neni nutny close, tenhle construct vse ukonci sam
        } catch (IOException e) {
            System.out.println("Problem pri psani do souboru");
        }

        System.out.println("Happy ending!");
    }
}
