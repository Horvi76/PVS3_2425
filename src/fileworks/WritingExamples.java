package fileworks;

import java.io.*;
import java.util.Arrays;

public class WritingExamples {
    public static void main(String[] args) throws IOException {
        DataExport de = new DataExport("data\\output1.txt");
        for (int i = 0; i < 1000; i++) {
            de.writeLine(String.valueOf((int)(Math.random()*4001)));
        }
        de.finishExport();

        //Scanner - nema write variantu

        //po znacich
        FileWriter fw = new FileWriter("data\\output2.txt");
        for (int i = 0; i < 1000; i++) {
//            fw.write((int)(Math.random()*4001));
            fw.write(String.valueOf((int)(Math.random()*4001)));
            fw.write('\n');
        }
        fw.close();

        //po radcich
        BufferedWriter bw = new BufferedWriter(new FileWriter("data\\output3.txt"),1);
        for (int i = 0; i < 10000; i++) {
            bw.write(String.valueOf((int)(Math.random()*4001)));
            bw.newLine();
        }
//        bw.flush();
//        bw.newLine();
//        bw.close();

        //vystup s formatem
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("data\\output4.txt")));
        for (int i = 0; i < 10000; i++) {
            pw.println((int) (Math.random() * 4001));
        }
        pw.close();
    }
}
