package networking.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BroadcastClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 54321;

        try(Socket socket = new Socket(host, port)){
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Pripojeno k broadcast serveru...");
            System.out.println("Slovem quit se odpojis od serveru");

            Thread serverReader = new Thread(() -> {
               try{
                   String serverMsg;
                   while ((serverMsg = serverInput.readLine()) != null){
                       System.out.println("\n " + serverMsg);
                       System.out.println("Zadej zpravu:");
                   }
               } catch (IOException e){
                   System.out.println(e.getMessage());
               }
            });
            serverReader.start();

            String line;
            while (true){
                System.out.println("Zadej zpravu:");
                line = userInput.readLine();
                if (line == null || "quit".equalsIgnoreCase(line)){
                    out.println("quit");
                    break;
                }
                out.println(line);
            }
            System.out.println("Ukoncuji klienta...");
            serverReader.join();

        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Program klienta ukoncen");
    }
}
