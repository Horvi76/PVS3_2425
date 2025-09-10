package fileworks;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadMultiples {

    static ArrayList<Integer> getNumbers(String filePath, int from, int to) throws IOException {
        ArrayList<Integer> evenNumbers = new ArrayList<>();
        String line;
        int number;
        for (int i = from; i <= to; i++) {
            BufferedReader br = new BufferedReader(new FileReader(filePath + i + ".txt"));
            while((line = br.readLine()) != null){
                number = Integer.parseInt(line);
                if (number % 2 == 0){
                    evenNumbers.add(number);
                }
            }
            br.close();
        }
        return evenNumbers;
    }

    static void printFile(ArrayList<Integer> numbers, String filePath) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
        for (int number : numbers){
            pw.println(number);
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> evens = getNumbers("data\\output", 1,4);
        printFile(evens, "evens.txt");
    }
}
