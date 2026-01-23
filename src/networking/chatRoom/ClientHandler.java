package networking.chatRoom;

import java.net.Socket;

public class ClientHandler extends Thread{
    Socket clientSocket;
    String clientID;

    public ClientHandler(Socket clientSocket, String clientID) {
        this.clientSocket = clientSocket;
        this.clientID = clientID;
    }

    @Override
    public void run() {
        // TODO: 23.01.2026 Komunikace s klientem
    }


    void handleCommand(String input){

    }
}
