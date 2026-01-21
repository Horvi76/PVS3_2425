package networking.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BroadcasterServer {

    private static final int PORT = 54321;
    public static final List<PrintWriter> clientOutputs =
            Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        System.out.println("Server starts at " + PORT);

        try (ServerSocket ss = new ServerSocket(PORT)){
            for(;;){
                Socket client  = ss.accept();
                System.out.println("Client connected from " + client.getPort());

                BroadcastClientHandler handler = new BroadcastClientHandler(client);
                handler.start();
            }
        } catch (IOException e ){
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void broadcast(String message){
        for(PrintWriter pw : clientOutputs){
            pw.println(message);
        }
    }
} class BroadcastClientHandler extends Thread{

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public BroadcastClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            //pridavam vystupni cestu do seznamu pro broadcast
            BroadcasterServer.clientOutputs.add(out);

            String received;
            while ((received = in.readLine())!=null){
                System.out.println("S: " + received);

                if ("quit".equalsIgnoreCase(received)){
                    break;
                }

                BroadcasterServer.broadcast("[" + socket.getPort() +"]: " + received);
            }
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Odpojil se");
            BroadcasterServer.clientOutputs.remove(out);
            try {
                socket.close();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
