package exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class Demo {

    static void infinity(String test){
        System.out.println(test);
        infinity(test);
    }

    static int biiiigSequence(int a){
        System.out.println(a);
        if (a < Integer.MAX_VALUE - 1)
            biiiigSequence (a+1);
        return a;
    }

    static int factorial(int n) throws FactorialException {
        if (n < 0){
            throw new FactorialException();
        }
        if (n == 1 || n == 0){
            return 1;
        }
        return n * factorial(n-1);
    }

    public static void main(String[] args) {
//        infinity("example");
       try{
//        biiiigSequence(0);

           List<String> lines = Files.readAllLines(Paths.get("nenajdes.txt"));

       } catch (StackOverflowError err){
//           err.printStackTrace();
           System.out.println("Ran out of Stack memory...");
       }
       catch (FileNotFoundException | NoSuchFileException e){
           System.out.println("Nenasel jsem soubor");
       }
       catch (IOException e){
           System.out.println("Obecny problem se souborem");
       }


//        throw new RuntimeException();
//        int result = 10 / 0;

//        throw new IOException();
//        FileReader fr = new FileReader("test");

        try {
            System.out.println(factorial(-5));
        } catch (FactorialException e) {
            e.printStackTrace();
        }
        System.out.println("Happy ending!");
    }
}
