package networking.chatRoom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RoomChatServer {
    final static int PORT = 11111;
    static int clientCounter = 1;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while (true){
                Socket client = serverSocket.accept();
                System.out.println("Pripojil se: " + client.getInetAddress() + ":"+client.getPort());

                ClientHandler handler = new ClientHandler(client, "CL_"+clientCounter);
                clientCounter++;
                handler.start();
            }
        } catch (IOException e){
            System.out.println("Chyba na serveru: " + e.getMessage());
        }
    }

}
