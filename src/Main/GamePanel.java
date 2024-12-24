package Main;

import GameObject.Apple;
import GameObject.Snake;
import UserInput.Key;
import UserInput.Mouse;
import Utilz.Images;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static Main.Game.gameWindow;
import static Utilz.Constants.GRASS;

/**
 * The GamePanel class represents the main drawing area for the Snake game. It
 * initializes the snake, apple, and input listeners, and delegates rendering
 * to the Game class.
 */
public class GamePanel extends JPanel {

    private final BufferedImage BACKGROUND;

    public static final int TILES_LENGTH = 33;
    public static final int TILES_PER_ROW = 19;
    public static final int TILES_PER_COL = 19;
    public static final int GAME_WIDTH = TILES_LENGTH * TILES_PER_ROW;
    public static final int GAME_HEIGHT = TILES_LENGTH * TILES_PER_COL;

    public static Apple apple;
    public static Snake snake;
    private static Key key;
    private static Mouse mouse;

    public static Game game;

    /**
     * Constructs the GamePanel, sets its size, loads images, and initializes
     * game objects (Snake, Apple). Attaches keyboard and mouse listeners.
     *
     * @param game the main Game instance used for rendering calls
     */
    public GamePanel(Game game) {
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

        BACKGROUND = Images.getImage(GRASS);
        snake = new Snake();
        apple = new Apple();
        key = new Key();
        mouse = new Mouse();
        addKeyListener(key);
        addMouseListener(mouse);

        GamePanel.game = game;
    }

    /**
     * Overridden paintComponent method. Clears the panel and then delegates
     * rendering to the Game instance via game.render(g).
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    /**
     * Draws the level background (a grid or checkerboard pattern). Optionally,
     * the green background code can be uncommented to switch from the red
     * checkerboard to a grass-like appearance (to be implemented in the menu
     * later). Also triggers a repaint of the top bar in the game window.
     *
     * @param g the Graphics object used for drawing
     */
    public void drawLevel(Graphics g) {
        this.setBorder(BorderFactory.createLineBorder(new Color(18, 21, 28), 33));

        for (int r = 0; r < TILES_PER_COL; r++) {
            for (int c = 0; c < TILES_PER_ROW; c++) {
                // green background:
                //g.drawImage(BACKGROUND, c * TILES_LENGTH, r * TILES_LENGTH,
                //     TILES_LENGTH, TILES_LENGTH, null);

                // red background:
                if (r % 2 != c % 2) {
                    g.setColor(new Color(102, 27, 28));
                } else {
                    g.setColor(new Color(83, 21, 22));
                }
                g.fillRect(c * TILES_LENGTH, r * TILES_LENGTH,
                        TILES_LENGTH, TILES_LENGTH);
            }
        }
        gameWindow.topBar.repaint();
    }
}
