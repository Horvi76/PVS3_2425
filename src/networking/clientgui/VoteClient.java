package networking.clientgui;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class VoteClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5555;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private final JLabel countLabel1 = new JLabel("0");
    private final JLabel countLabel2 = new JLabel("0");
    private final JLabel countLabel3 = new JLabel("0");

    private final JRadioButton opt1 = new JRadioButton("Option 1");
    private final JRadioButton opt2 = new JRadioButton("Option 2");
    private final JRadioButton opt3 = new JRadioButton("Option 3");
    private final JButton voteButton = new JButton("Vote");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VoteClient().startUI());
    }

    private void startUI() {
        JFrame frame = new JFrame("Voting Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ButtonGroup group = new ButtonGroup();
        group.add(opt1);
        group.add(opt2);
        group.add(opt3);
        opt1.setSelected(true);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        main.add(row("Option 1 votes:", countLabel1));
        main.add(row("Option 2 votes:", countLabel2));
        main.add(row("Option 3 votes:", countLabel3));


        main.add(opt1);
        main.add(opt2);
        main.add(opt3);

        main.add(voteButton);

        frame.setContentPane(main);
        frame.setSize(360,360);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        voteButton.addActionListener(e -> sendVote());

        connect();
    }

    private JPanel row(String left, JLabel right) {
        JPanel p = new JPanel(new BorderLayout(8, 0));
        p.add(new JLabel(left), BorderLayout.WEST);
        p.add(right, BorderLayout.EAST);
        return p;
    }

    private void connect() {
        try {
            socket = new Socket(HOST, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            //tady zacinu gui multithread
            new Thread(this::listenLoop, "server-listener").start();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Could not connect to server at " + HOST + ":" + PORT,
                    "Connection error",
                    JOptionPane.ERROR_MESSAGE);
            voteButton.setEnabled(false);
        }
    }

    private void listenLoop() {
        //tohle uz je klasicka chat verze
        try {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("COUNTS")) {
                    String[] parts = line.split("\\s+");
                    if (parts.length == 4) {
                        int a = Integer.parseInt(parts[1]);
                        int b = Integer.parseInt(parts[2]);
                        int c = Integer.parseInt(parts[3]);
                        //update gui, ktery je nezavisly na komunikaci...
                        SwingUtilities.invokeLater(() -> {
                            countLabel1.setText(String.valueOf(a));
                            countLabel2.setText(String.valueOf(b));
                            countLabel3.setText(String.valueOf(c));
                        });
                    }
                }
            }
        } catch (IOException | NumberFormatException ex) {
            SwingUtilities.invokeLater(() -> {
                voteButton.setEnabled(false);
                JOptionPane.showMessageDialog(null,
                        "Disconnected from server.",
                        "Disconnected",
                        JOptionPane.WARNING_MESSAGE);
            });
        } finally {
            //slusne zakonceni..
            closeQuietly();
        }
    }

    private void sendVote() {
        if (out == null) return;

        int option = opt1.isSelected() ? 1 : opt2.isSelected() ? 2 : 3; //to jde taky :)

        out.println("VOTE " + option);
    }

    private void closeQuietly() {
        try {
            if (socket != null) socket.close();
        } catch (IOException ignored) {}
    }
}
