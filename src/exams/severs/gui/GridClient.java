package exams.severs.gui;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class GridClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5555;
    private static final int SIZE = 10;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private final JLabel[][] cells = new JLabel[SIZE][SIZE];

    private final JTextField rowField = new JTextField(2);
    private final JTextField colField = new JTextField(2);
    private final JButton reserveButton = new JButton("Reserve");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GridClient().startUI());
    }

    private void startUI() {
        JFrame frame = new JFrame("10x10 Reservation Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel grid = new JPanel(new GridLayout(SIZE, SIZE, 2, 2));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                JLabel b = new JLabel();
                b.setPreferredSize(new Dimension(40, 40));

                // to u tlacitka zobrazi, jake ma souradnice
                b.setText(r + "," + c);

                cells[r][c] = b;
                grid.add(b);
            }
        }

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(new JLabel("Row (0-9):"));
        controls.add(rowField);
        controls.add(new JLabel("Col (0-9):"));
        controls.add(colField);
        controls.add(reserveButton);

        reserveButton.addActionListener(e -> {
            //todo - poslat tlacitku instrukci
        });

        frame.setLayout(new BorderLayout());
        frame.add(controls, BorderLayout.NORTH);
        frame.add(grid, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        connect();
    }

    private int parseField(String s) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return -1; }
    }

    private void connect() {
        try {
            socket = new Socket(HOST, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            //todo - novy thread a zacni poslouchat
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Could not connect to server at " + HOST + ":" + PORT + "\n" + ex.getMessage(),
                    "Connection error",
                    JOptionPane.ERROR_MESSAGE);
            setControlsEnabled(false);
        }
    }

    private void listenLoop() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("RESERVED")) {
                    //todo
                } else if (line.startsWith("ERROR")) {
                    //todo
                }
            }
        } catch (IOException | NumberFormatException ex) {
            SwingUtilities.invokeLater(() -> {
                setControlsEnabled(false);
                JOptionPane.showMessageDialog(null,
                        "Disconnected from server.",
                        "Disconnected",
                        JOptionPane.WARNING_MESSAGE);
            });
        } finally {
            closeQuietly();
        }
    }

    private void sendReserve(int r, int c) {
        if (out == null) return;

        //overte, ze souradnice jsou ok
        if (true) {
            JOptionPane.showMessageDialog(null, "Souradnice musi byt 0..9", "Input error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        out.println("RESERVE " + r + " " + c);
    }

    private void markReserved(int r, int c) {
        if (r < 0 || r >= SIZE || c < 0 || c >= SIZE) return;

        JLabel b = cells[r][c];
        b.setText("X"); // Obsadi misto
    }

    private void setControlsEnabled(boolean enabled) {
        //pozapina...
        reserveButton.setEnabled(enabled);
        rowField.setEnabled(enabled);
        colField.setEnabled(enabled);
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c].setEnabled(enabled && cells[r][c].isEnabled());
            }
        }
    }

    private void closeQuietly() {
        try { if (socket != null) socket.close(); } catch (IOException ignored) {}
    }
}
