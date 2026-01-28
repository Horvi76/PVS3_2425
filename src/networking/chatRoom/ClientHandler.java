package networking.chatRoom;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
    Socket clientSocket;
    String clientID;

    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket, String clientID) {
        this.clientSocket = clientSocket;
        this.clientID = clientID;
    }

    @Override
    public void run() {
        // TODO: 23.01.2026 Komunikace s klientem
    }


    void send(String message){
        out.println(message);
    }

    void handleCommand(String input){

    }
}
