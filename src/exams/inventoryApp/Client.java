package exams.inventoryApp;

import networking.ser.Point;
import networking.ser.Result;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static int messageIndex = 1;

    static void printLegend(){
        System.out.println("Zvol variantu");
        System.out.println("1: Order - put");
        System.out.println("2: Order - take");
        System.out.println("3: status");
        System.out.println("0: quit");
    }

    static void sendOrder(Scanner sc, OrderType type, ObjectOutputStream oos) throws IOException {
        System.out.println("ItemName:");
        String item = sc.next();
        System.out.println("Item quantity:");
        int itemAmount = sc.nextInt();
        oos.writeObject(new Order(messageIndex, type, item, itemAmount));
        oos.flush();
        messageIndex++;
    }

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 11111);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner sc = new Scanner(System.in);
        ) {
            int input;
            boolean running = true;
            while (running){
                printLegend();
                input = sc.nextInt();
                //ted neco budu posilat
                switch (input){
                    case 1 -> sendOrder(sc, OrderType.PUT, out);
                    case 2 -> sendOrder(sc, OrderType.TAKE, out);
                    case 3 -> {
                        out.writeObject(new StateRequest(messageIndex));
                        out.flush();
                        messageIndex++;
                    }
                    case 0 -> {
                        running = false;
                        continue;
                    }
                }
                //ted mi neco prijde...
                StateResponse response = (StateResponse) in.readObject();
                System.out.println("Odpoved na " + response.requestId);
                System.out.println(response.items);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
