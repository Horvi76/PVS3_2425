package networking.udp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpCalcClient
{
    public static void main(String[] args) {
        try(
                Socket socket = new Socket("localhost", 55551);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner sc = new Scanner(System.in);
            ){
            System.out.println("X: ");
            double x = sc.nextDouble();
            System.out.println("Y: ");
            double y = sc.nextDouble();

            out.writeDouble(x);
            out.writeDouble(y);
            out.flush();

            double distance = in.readDouble();
            System.out.println("Dist: " + distance);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
