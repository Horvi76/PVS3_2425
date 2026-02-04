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

    public String getClientID() {
        return clientID;
    }

    @Override
    public void run() {
        try {
            //tady cte info od serveru
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()),true);
            //tudy komunikje clinet k serveru
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Successful connect"); // tohle se vypise u klienta
            RoomManager.ROOM_MANAGER.joinLobby(this);
            String received;

            while ((received = in.readLine()) != null){
                if (received.equalsIgnoreCase("/quit")){
                    break;
                }
                System.out.println("SERVER_LOG (" + clientID + "): " + received);
                CommandRouter.handleCommand(this, received);
            }

        } catch (IOException e) {
            System.out.println("Disconnected: " + clientID + " (" + e.getMessage() + ")");
        }finally {
            out.close();
        }
    }


    void send(String message){
        out.println(message);
    }


}
