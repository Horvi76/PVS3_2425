package exams.severs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiClientJobQueueServer {
    private static final int PORT = 11111;
    private static final List<String> tasks =
            Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        System.out.println("Server spoustim na portu " + PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                // pro kazdeho clienta vlastni thread...
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server konci...");
    }

    private static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        break;
                    }
                    line = line.trim();

                    if (line.equalsIgnoreCase("quit")) {
                        out.println("Konec, seeya!");
                        break;
                    } else if (line.startsWith("push ")) {
                        // Pridani tasku
                        String task = line.substring("push ".length()).trim(); //separuje slovo push od zbytku
                        //teoreticky muze byt i firstIndexOf() pro mezeru ci jine...
                        if (task.isEmpty()) {
                            out.println("Error: za push nic neni");
                        } else {
                            tasks.add(task);
                            out.println("Pridano do listu: " + task);
                        }
                    } else if (line.equalsIgnoreCase("list")) {
                        // Vraceni celeho task-listu
                        synchronized (tasks) {
                            if (tasks.isEmpty()) {
                                out.println("Task list je prazdny: []");
                            } else {
                                out.println("Task list: " + tasks.toString());
                            }
                        }
                    } else {
                        out.println("Neznamy command. Pouzijte: push <desc>, list nebo quit.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Client disconnect
                System.out.println("Client se odpojil: " + socket.getInetAddress() + ":" + socket.getPort());
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
