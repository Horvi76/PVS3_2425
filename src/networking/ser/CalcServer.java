package networking.ser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(11111)){
            while (true){
                Socket socket = ss.accept();
                System.out.println("Pripojil se client: " + socket.getPort());
                new Thread(() -> handleClient(socket)).start();
            }
        } catch (IOException e){
            System.out.println("Client err: " + e.getMessage());
        }
    }

    static void handleClient(Socket socket){
        try(
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
            ) {

            Point p = (Point) in.readObject();

            String quadrant = getQuadrant(p);
            double distance = Math.sqrt(p.x() * p.x() + p.y()* p.y());
            Result r = new Result(distance, quadrant);
            out.writeObject(r);
            out.flush();

        } catch (IOException | ClassNotFoundException e){
            System.out.println("Client err: " + e.getMessage());
        }
    }

    static String getQuadrant(Point p){
        if (p.x() >= 0 && p.y() >= 0) return "Quadrant I";
        if (p.x() < 0 && p.y() >= 0) return "Quadrant II";
        if (p.x() < 0 && p.y() < 0) return "Quadrant III";
        if (p.x() >= 0 && p.y() < 0) return "Quadrant IV";
        return "Impossible";
    }
}
