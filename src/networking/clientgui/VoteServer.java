package networking.clientgui;
import java.io.*;
import java.net.*;
import java.util.*;

public class VoteServer {
    private static final int PORT = 5555;

    //trochu zvlastnost
    private final int[] counts = new int[3];

    // tohle uz zname...
    private final Set<PrintWriter> clientOutputs = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws IOException {
        new VoteServer().start();
    }

    private void start() throws IOException {
        System.out.println("VoteServer listening on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getRemoteSocketAddress());
                new Thread(() -> handleClient(socket), "client-" + socket.getPort()).start();
            }
        }
    }

    private void handleClient(Socket socket) {
        PrintWriter out = null;

        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

            out = pw;
            clientOutputs.add(out);

            // sotva se pripoji, tak ho aktualizuju...
            sendCounts(out);

            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("VOTE")) {
                    // example: VOTE 1|2|3
                    String[] parts = line.split("\\s+");
                    if (parts.length == 2) {
                        int option = parseOption(parts[1]);
                        if (option >= 1 && option <= 3) {
                            counts[option - 1]++;
                            broadcastCounts();
                        } else {
                            out.println("ERROR Invalid option (use 1..3)");
                        }
                    } else {
                        out.println("ERROR Format: VOTE <1|2|3>");
                    }
                } else {
                    out.println("ERROR Unknown command");
                }
            }
        } catch (IOException e) {
            System.out.println("Client IO error: " + e.getMessage());
        } finally {
            if (out != null) clientOutputs.remove(out);
            System.out.println("Client disconnected: " + socket.getRemoteSocketAddress());
        }
    }

    private int parseOption(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void broadcastCounts() {
        synchronized (clientOutputs) {
            for (PrintWriter out : clientOutputs) {
                sendCounts(out);
            }
        }
    }

    private void sendCounts(PrintWriter out) {
        int a = counts[0];
        int b = counts[1];
        int c = counts[2];
        out.println("COUNTS " + a + " " + b + " " + c);
    }
}
