package fileworks;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Counting {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("inputs.txt");
        int input;

        int wordCount = 1;
        int sentenceCount = 0;
        int lineCount = 1;
        while ((input = reader.read()) != -1){
            System.out.print((char) input);
            if (input == '\n'){
                lineCount++;
                wordCount++;
            }
            if (input == '.' || input == '!' || input == '?'){
                sentenceCount++;
            }
            if (input == ' '){
                wordCount++;
            }
        }
        reader.close();
        System.out.println();
        System.out.println("Lines: " + lineCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Words: " + wordCount);
    }
}
