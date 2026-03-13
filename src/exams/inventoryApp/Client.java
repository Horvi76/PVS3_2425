package exams.inventoryApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 11111;

        try (Socket socket = new Socket(host, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader serverInput = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("Pripojeno " + host + ":" + port);
            System.out.println("Commands: order/status/quit");

            while (true) {
                System.out.print(">> ");
                String command = userInput.readLine();
                if (command == null) {
                    // Uzivatel ukonci konzoli
                    break;
                }
                command = command.trim();
                if (command.contains("order")){
                    System.out.println("put/take");
                    String subCommand = userInput.readLine();
                    System.out.println("Item");
                    String item = userInput.readLine();
                    int qty = Integer.parseInt(userInput.readLine());
//                    Scanner sc = new Scanner();
//                    sc.delimiter(";");
                } else if (command.contains("status")) {
                    System.out.println("posli status, cekej odpoved...");
                } else {
                    System.out.println("Unknown...");
                    continue;
                }

                // Pripad pro ukonceni
                if (command.equalsIgnoreCase("quit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client stopped.");
    }
}
