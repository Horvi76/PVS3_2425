package networking.chatRoom;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

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
        try {
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Successful connect");
            RoomManager.ROOM_MANAGER.joinLobby(this);


        } catch (IOException e) {
            System.out.println("Disconnected: " + clientID + " (" + e.getMessage() + ")");
        }finally {
            out.close();
        }
    }


    void send(String message){
        out.println(message);
    }

    void handleCommand(String input){

    }
}
