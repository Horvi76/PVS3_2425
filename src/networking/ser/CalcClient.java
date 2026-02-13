package networking.ser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CalcClient {

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 11111);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner sc = new Scanner(System.in);
        ) {
            System.out.println("Zadej X: ");
            double x = sc.nextDouble();
            System.out.println("Zadej Y: ");
            double y = sc.nextDouble();

            out.writeObject(new Point(x, y));
            out.flush();

            Result res = (Result) in.readObject();
            System.out.println("Distance: " + res.distance());
            System.out.println("Location: " + res.quadrant());
        } catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}

