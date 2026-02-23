package networking.clientgui;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class EventLoggerServer {
    public static final int PORT = 5003;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("EventLoggerServer listening on port " + PORT);

            while (true) {
                Socket socket = server.accept();
                new Thread(() -> handle(socket), "event-client").start();
            }
        }
    }

    private static void handle(Socket socket) {
        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = in.readLine()) != null) {
                // Ukazka radky: LOG|INFO|1700000000000|Hello
                System.out.println("[LOG] " + socket.getRemoteSocketAddress() + " -> " + line);
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
