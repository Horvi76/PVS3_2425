package networking.clientgui;
import javax.swing.*;
import java.awt.*;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EventLoggerClient extends JFrame {

    private final JTextField hostField = new JTextField("localhost");
    private final JTextField portField = new JTextField("5003");
    private final JComboBox<String> levelBox = new JComboBox<>(new String[]{"INFO", "WARN", "ERROR"});
    private final JTextField messageField = new JTextField();
    private final JButton sendButton = new JButton("Send");
    private final JLabel statusLabel = new JLabel("Ready");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventLoggerClient().setVisible(true));
    }

    public EventLoggerClient() {
        super("Event Logger Client (Fire-and-forget)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 180);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new GridLayout(2, 4, 8, 8));
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        top.add(new JLabel("Host:"));
        top.add(hostField);
        top.add(new JLabel("Port:"));
        top.add(portField);
        top.add(new JLabel("Level:"));
        top.add(levelBox);
        top.add(new JLabel("Message:"));
        top.add(messageField);

        JPanel bottom = new JPanel(new BorderLayout(8, 8));
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        bottom.add(sendButton, BorderLayout.EAST);
        bottom.add(statusLabel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(top, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendEvent());
        messageField.addActionListener(e -> sendEvent());
    }

    private void sendEvent() {
        String host = hostField.getText().trim();
        int port;
        try {
            port = Integer.parseInt(portField.getText().trim());
        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid port.");
            return;
        }

        String level = (String) levelBox.getSelectedItem();
        String msg = messageField.getText().trim();
        if (msg.isEmpty()) {
            statusLabel.setText("Message is empty.");
            return;
        }

        sendButton.setEnabled(false);
        statusLabel.setText("Sending...");

        String line = "LOG|" + level + "|" + System.currentTimeMillis() + "|" + msg;

        // Description
        new Thread(() -> {
            try (Socket s = new Socket(host, port);
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8), true)) {
                out.println(line);

                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Sent.");
                    messageField.setText("");
                    messageField.requestFocusInWindow();
                    sendButton.setEnabled(true);
                });

            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Send failed: " + ex.getMessage());
                    sendButton.setEnabled(true);
                });
            }
        }, "event-sender").start();
    }
}