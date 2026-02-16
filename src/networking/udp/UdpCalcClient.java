package networking.udp;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpCalcClient {
    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket();
            Scanner sc = new Scanner(System.in)){

            System.out.println("X:");
            double x = sc.nextDouble();
            System.out.println("Y:");
            double y = sc.nextDouble();
            String msg = x+","+y;
            byte[] data = msg.getBytes();

            DatagramPacket packet = new DatagramPacket(
                    data, data.length,
                    InetAddress.getByName("localhost"),
                    55552
            );
            socket.send(packet);

            byte[] buffer = new byte[1024];
            DatagramPacket resp = new DatagramPacket(buffer, buffer.length);
            socket.receive(resp);

            String distance = new String(resp.getData(), 0, resp.getLength());
            System.out.println("Dist: " + distance);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
