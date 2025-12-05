package exams;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MazeGrid extends JFrame implements KeyListener {

    private static final int FREE = 0;
    private static final int WALL = 1;
    private static final int FINISH = 2;

    // 0 = free, 1 = wall, 2 = finish
    private final int[][] grid = {
            {FREE, FREE, FREE, WALL, FREE, FREE, FREE},
            {WALL, WALL, FREE, WALL, FREE, WALL, FREE},
            {FREE, FREE, FREE, FREE, FREE, WALL, FREE},
            {FREE, WALL, WALL, WALL, FREE, FREE, FREE},
            {FREE, FREE, FREE, WALL, FREE, WALL, FREE},
            {FREE, WALL, FREE, FREE, FREE, FREE, FINISH}
    };

    private final int rows = grid.length;
    private final int cols = grid[0].length;

    // levy horni roh (0,0)
    private int playerRow = 0;
    private int playerCol = 0;

    // TODO: track number of steps
    private int steps = 0;

    private JLabel stepsLabel;
    private JPanel gridPanel;
    private JLabel[][] cellLabels;

    public MazeGrid() {
        super("Maze Game (Grid) - Assignment");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(750, 750);

        // Info panel
        stepsLabel = new JLabel("Steps: 0");
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.add(stepsLabel);
        infoPanel.add(Box.createHorizontalStrut(20));
        add(infoPanel, BorderLayout.SOUTH);

        // labels
        gridPanel = new JPanel(new GridLayout(rows, cols));
        cellLabels = new JLabel[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setOpaque(true);
                label.setBorder(new LineBorder(Color.GRAY));
                cellLabels[r][c] = label;
                gridPanel.add(label);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        addKeyListener(this);
        setFocusable(true);

        setLocationRelativeTo(null);

        updateGridColors();
        updateInfoLabels();
    }

    /**
     * Pohyb hrace od dRow, dCol
     *
     * Pravidla:
     *  - Neprojde mimo grid.
     *  - Neprojde do zdi
     *  - Pokud OK:
     *       - update playerRow & playerCol
     *       - pocet steps++
     *       - updateGridColors()
     *       -  updateInfoLabels()
     *  - Pokud pozice hrace == FINISH:
     *       - Vyplivnout dialog, kolik bylo kroku a ze OK.
     */
    private void movePlayer(int dRow, int dCol) {
        int newRow, newCol;
        // TODO:
        // 1) spocitat pohyb
        // 2) overit (0 <= newRow < rows, 0 <= newCol < cols)
        // 3) Overit, ze novy souradnice nejsou zed
        // 4) pokud OK: update playerRow / playerCol a steps++
        // 5) Call updateGridColors() and updateInfoLabels()
        // 6) pokud nova souradnice je zaroven FINISH, tak ukoncit a pogratulovat :) a vypsat pocet kroku
    }

    /**
     * Pouzivat, nesahat
     */
    private void updateGridColors() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JLabel label = cellLabels[r][c];
                if (grid[r][c] == WALL) {
                    label.setBackground(Color.DARK_GRAY);
                    label.setText("");
                } else if (grid[r][c] == FINISH) {
                    label.setBackground(Color.GREEN);
                    label.setText("F");
                } else {
                    label.setBackground(Color.WHITE);
                    label.setText("");
                }
            }
        }

        JLabel playerLabel = cellLabels[playerRow][playerCol];
        playerLabel.setBackground(Color.RED);
        playerLabel.setText("P");
    }

    /**
     * Update, muzete vyuzit
     */
    private void updateInfoLabels() {
         stepsLabel.setText("Steps: " + steps);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO: POHYB: movePlayer(...)
        e.getKeyChar();//se bude hodit
        // - w arrow -> movePlayer(-1, 0)
        // - s arrow -> movePlayer(1, 0)
        // - a arrow -> movePlayer(0, -1)
        // - d arrow -> movePlayer(0, 1)
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MazeGrid game = new MazeGrid();
            game.setVisible(true);
        });
    }
}
