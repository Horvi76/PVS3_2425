package networking.udp;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class UdpCalc {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(55552)){

            byte[] buffer = new byte[1024];

            while (true){
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, buffer.length);
                String[] parts = msg.split(",");

                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);

                double dist = Math.sqrt(x*x + y*y);
                String response = Double.toString(dist);

                byte[] responseBytes = response.getBytes();

                DatagramPacket resp = new DatagramPacket(
                        responseBytes,
                        responseBytes.length,
                        packet.getAddress(),
                        packet.getPort()
                );
                socket.send(resp);

            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
