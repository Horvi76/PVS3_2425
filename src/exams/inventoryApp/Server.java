package exams.inventoryApp;

import networking.ser.Point;
import networking.ser.Result;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    public static final Map<String, Integer> inventory = new ConcurrentHashMap<>();

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
            Object request;
            while (true){
            request = in.readObject();
                //zpracuj a posli aktualizovany stav
                if (request instanceof Order){
                    manageOrder((Order) request);
                    out.writeObject(new StateResponse(((Order) request).requestId, getInventorySnapshot()));
                    out.flush();
                } else if (request instanceof StateRequest) {
                    out.writeObject(new StateResponse(((StateRequest) request).requestId, getInventorySnapshot()));
                    out.flush();
                } else {
                    //kdyby se pripojil nekdo jinak, nez klientem...
                    System.out.println("Unkown object entered!");
                }
            }

        } catch (IOException | ClassNotFoundException e){
            System.out.println("Client err: " + e.getMessage());
        }
    }

    static Map<String, Integer> getInventorySnapshot(){
        Map<String, Integer> snapshot;
        synchronized (inventory) {
            snapshot = new HashMap<>(inventory);
        }
        return snapshot;
    }

    static void manageOrder(Order order){
        System.out.println("---- req #" + order.requestId + " ----" );
        System.out.println("Inventory pre-change:");
        System.out.println(inventory);
        if (order.type == OrderType.PUT){
            //Existuje chytrejsi cesta...
            if (inventory.containsKey(order.itemName)){
                inventory.replace(order.itemName, inventory.get(order.itemName) + order.qty);
            } else {
                inventory.put(order.itemName, order.qty);
            }

        }
        if (order.type == OrderType.TAKE){
            if (inventory.containsKey(order.itemName) && inventory.get(order.itemName) >= order.qty){
                inventory.replace(order.itemName, inventory.get(order.itemName) - order.qty);
            } else {
                System.out.println("Client tried taking nonexistent item or qty was outside inventory bounds");
            }
        }
        System.out.println("Inventory post-change:");
        System.out.println(inventory);
        System.out.println("----");
    }

}
