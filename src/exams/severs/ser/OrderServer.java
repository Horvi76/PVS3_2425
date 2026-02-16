package exams.severs.ser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class OrderServer {

    private static final int PORT = 5001;
    private static final Map<String, Double> CATALOG = Map.of(
            "pen", 1.50,
            "notebook", 3.99,
            "stapler", 7.25,
            "mouse", 12.90
    );

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("OrderServer listening on port " + PORT);

            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connected: " + socket.getRemoteSocketAddress());
                new Thread(() -> handleClient(socket), "client-handler").start();
            }
        }
    }

    private static void handleClient(Socket socket) {
        try (socket;
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            //bude Order
            Object obj = in.readObject();


            CATALOG.get("jmeno"); // vrati cenu

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
