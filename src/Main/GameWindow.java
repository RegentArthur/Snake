package Main;

import GameStates.GameState;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.Insets;

/**
 * The GameWindow class is responsible for creating and displaying the main
 * application window (JFrame). It also contains an inner TopBar class
 * for rendering score information and other UI elements at the top.
 */
public class GameWindow {

    /**
     * The TopBar class is a JPanel added to the top (NORTH) of the JFrame,
     * responsible for displaying the current score or other status messages.
     */
    public class TopBar extends JPanel {
        /**
         * Constructs the TopBar by setting its layout and adding it to the
         * GameWindow's frame at the NORTH position.
         */
        public TopBar() {
            setLayout(new GridBagLayout());
            setUpField(this);
            frame.add(this, BorderLayout.NORTH);
        }

        /**
         * Overridden paintComponent to update the score label if the game
         * is in PLAY mode.
         *
         * @param g the Graphics object for drawing
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (GameState.state == GameState.PLAY) {
                scoreLabel.setText("Score: " + GamePanel.snake.getScore());
            }
        }
    }

    public JFrame frame;
    public TopBar topBar;
    private JLabel scoreLabel;

    /**
     * Constructs the GameWindow, setting up the main JFrame properties,
     * attaching the TopBar, and adding the provided GamePanel to the frame.
     *
     * @param gamePanel the panel where the game is rendered
     */
    public GameWindow(GamePanel gamePanel) {
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        topBar = new TopBar();

        // Add the game panel to the center of the frame
        frame.add(gamePanel);
        frame.pack();

        // Request focus for the game panel to ensure it receives key events
        gamePanel.requestFocus();

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Configures the top bar's appearance and layout. Sets size, color,
     * alignment, etc. Also initializes and styles the score label.
     *
     * @param topBar the panel to be configured as the top bar
     */
    public void setUpField(JPanel topBar) {
        topBar.setBackground(new Color(27, 34, 44));
        topBar.setPreferredSize(
                new Dimension(450, GamePanel.TILES_LENGTH * 2)
        );
        topBar.setBorder(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 40, 0, 0);

        scoreLabel = new JLabel("Welcome to Snake!");
        topBar.add(scoreLabel, gbc);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Serif", Font.ITALIC, 20));
    }
}
