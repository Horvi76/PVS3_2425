package networking.udp;

import fileworks.DataExport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;

public class TcpCalc
{
    public static void main(String[] args) {
        try(ServerSocket ss = new ServerSocket(55551)){
            while (true){
                Socket socket = ss.accept();
                new Thread( () -> {
                    try (
                            DataInputStream input = new DataInputStream(socket.getInputStream());
                            DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                            ){
                        double x = input.readDouble();
                        double y = input.readDouble();

                        double distance = Math.sqrt(x*x + y*y);
                        output.writeDouble(distance);
                        output.flush();

                    } catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                }).start();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
