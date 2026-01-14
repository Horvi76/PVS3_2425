package networking.reverse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReverseClient {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 11111;

        try(Socket client = new Socket(host, port)){
            PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String serverInput = reader.readLine();
            if (serverInput == null){
                System.out.println("Server terminated");
                return;
            }
            pw.println(new StringBuilder(serverInput).reverse());
            System.out.println("Server: " + reader.readLine());
        } catch (IOException e){
            System.out.println("Comms error: " + e.getMessage());
        }
    }
}
