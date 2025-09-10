package fileworks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ReadingExamples {

    public static void main(String[] args) throws IOException {
        File file = new File("data\\countries.txt");
        if (file.exists() && file.isFile()){
            DataImport di = new DataImport(file.getPath());
            while (di.hasNext()){
                System.out.println(di.readLine());
            }
            di.finishImport();
            System.out.println();
        }
//        Scanner sc = new Scanner("hello,world,its,beautiful,day");
//        sc.useDelimiter(",");
//        while (sc.hasNext()){
//            System.out.println(sc.next());
//        }
        Scanner sc = new Scanner(file);
        sc.useLocale(Locale.US);
        sc.useDelimiter(";");
        while (sc.hasNext()){
            System.out.println(sc.next());
        }
        sc.close();

        FileReader reader = new FileReader(file);
        int input;
        while ((input = reader.read()) != -1){
            System.out.print((char) input);
        }
        reader.close();


        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
        bufferedReader.close();

        List<String> lines = Files.readAllLines(Paths.get("data\\countries.txt"));
        System.out.println(lines);
        System.out.println(lines.size());
//        for (String oneLine : lines){
//            //parse...
//        }
    }

}