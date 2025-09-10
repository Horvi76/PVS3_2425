package exams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoginValidation {

    static List<String> pinCodes;


    static List<String> usernames;
    static int totalValidAmount = 0;
    static int totalLines = 0;
    static ArrayList<String> findPrintValid(String fileName) throws IOException{
        ArrayList<String> validLines = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        totalLines += lines.size();
        String[] lineParams;
        for (String line : lines){
            lineParams = line.split("=");
            //je validni?
            if (usernames.contains(lineParams[0]) && usernames.indexOf(lineParams[0]) == pinCodes.indexOf(lineParams[1])){
                totalValidAmount++;
                validLines.add(line);
            }
        }
        System.out.println(fileName + ": " + validLines.size());
        return validLines;
    }

    public static void main(String[] args) throws IOException {
        pinCodes = Files.readAllLines(Paths.get("data\\logins\\AllPINs.txt"));
        usernames = Files.readAllLines(Paths.get("data\\logins\\usernames.txt"));
        ArrayList<String> toPush;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("data\\logins\\validLogins.txt")));
        for (int i = 1; i <= 5; i++) {
            toPush = findPrintValid("data\\logins\\attempts_" + i + ".txt");
            for (String line : toPush){
                pw.println(line);
            }
        }
        pw.close();
        System.out.println("Total valid: " + totalValidAmount);
        System.out.println("Total lines: " + totalLines);
        System.out.println(((double) totalValidAmount / totalLines) * 100 + " %");

    }
}
