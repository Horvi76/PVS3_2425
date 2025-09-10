package fileworks;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class Ciphering {


    public static void main(String[] args) throws IOException {
        Caesar cipher = new Caesar(3, "Caesar cipher");
        List<String> lines = Files.readAllLines(Paths.get("data\\cipherTest.txt"));

        System.out.println("Original");
        System.out.println(lines);
        //pro ucely debugu, ulozit cely soubor do promenne
        ArrayList<String> encryptedLines = new ArrayList<>();
        for (String line : lines){
            encryptedLines.add(cipher.encrypt(line));
        }

        System.out.println("Encrypted:");
        System.out.println(encryptedLines);

        //nahazet do souboru:
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("data\\encryptedTest.txt")));
        for (String line : encryptedLines){
            writer.println(line);
        }
        writer.close();

        lines = Files.readAllLines(Paths.get("data\\encryptedTest.txt"));
        System.out.println("Decrypted");
        for (String line : lines){
            System.out.println(cipher.decrypt(line));
        }

    }
}
abstract class Cipher{
    int key;
    String name;

    public Cipher(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cipher name:" + name + " secret: " + key;
    }

    abstract String encrypt(String input);

    abstract String decrypt(String encryptedInput);

}
class Caesar extends Cipher{

    public Caesar(int key, String name) {
        super(key, name);
    }

    @Override
    String encrypt(String input) {
        StringBuilder result = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String lowerMessage = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isAlphabetic(lowerMessage.charAt(i))){
                int position = alphabet.indexOf(lowerMessage.charAt(i));
                if (position + key >= alphabet.length()){
                    result.append(alphabet.charAt(position + key - alphabet.length()));
                } else {
                    result.append(alphabet.charAt(position + key));
                }
            } else {
                result.append(lowerMessage.charAt(i));
            }
        }

        return result.toString();
    }

    @Override
    String decrypt(String encryptedInput) {
        int tmp = key;
        key = 26 - key;
        String result = encrypt(encryptedInput);
        key = tmp;
        return result;
    }
}