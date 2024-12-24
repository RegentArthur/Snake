package Main;

/*
 * The Game class sets up a game loop running on a separate thread, where we
 * periodically call update() and render() based on time intervals. For a simple
 * Snake game, this might be overkill, but it demonstrates how to set up a
 * multi-threaded architecture in Java using Runnable.
 *
 * <p>When the thread starts, it calls the run() method. That method continuously
 * checks if it's time to render a frame and/or update the game logic.</p>
 */

import Buttons.MenuInterface;
import GameStates.GameState;
import GameStates.MenuState;
import java.awt.Graphics;

public class Game implements Runnable {
    private Thread gameThread;

    // These values control the speed of the snake, may add as a feature in the menu later
    private static final int FPS = 120;
    private static final int UPS = 3;
    private static final long NANO_PER_FRAME = 14_000_000 / FPS;
    private static final long NANO_PER_UPDATE = 14_000_000 / UPS;

    private static GamePanel gamePanel;
    public static GameWindow gameWindow;
    private static MenuState menuState;
    public static MenuInterface menuInterface;

    /**
     * Constructs the Game object, initializing the GamePanel and GameWindow,
     * setting up the menu interface/state, and starting the game loop thread.
     */
    public Game() {
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);

        menuInterface = new MenuInterface();
        menuState = new MenuState();

        gameWindow.frame.add(menuState);
        gameWindow.frame.setVisible(true);

        startGameLoop();
    }

    /**
     * Creates and starts the game loop thread.
     */
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The main game loop. It continuously checks for rendering and update
     * intervals, measured in nanoseconds. If enough time has passed since
     * the last frame/update, it triggers repainting or updating accordingly.
     */
    @Override
    public void run() {
        long startTimeFrames = System.nanoTime();
        long startTimeUpdates = System.nanoTime();

        while (true) {
            long currentTimeFrames = System.nanoTime();
            long currentTimeUpdates = System.nanoTime();

            // Check if it's time to render a new frame.
            if (currentTimeFrames - startTimeFrames >= NANO_PER_FRAME) {
                if (GameState.state == GameState.PLAY) {
                    gamePanel.repaint();
                }
                startTimeFrames = currentTimeFrames;
            }

            // Check if it's time to update the game logic.
            if (currentTimeUpdates - startTimeUpdates >= NANO_PER_UPDATE) {
                update();
                startTimeUpdates = currentTimeUpdates;
            }
        }
    }

    /**
     * Renders the current game state. If in PLAY mode, draws the game level,
     * snake, and apple. If in MENU mode, you'd delegate to the menu state.
     *
     * @param g the Graphics object used to draw on screen
     */
    public void render(Graphics g) {
        if (GameState.state == GameState.PLAY) {
            gamePanel.drawLevel(g);
            GamePanel.apple.render(g);
            GamePanel.snake.render(g);
        }
    }

    /**
     * Updates the game objects (e.g., moves the snake) only if the game
     * is in PLAY mode.
     */
    private void update() {
        if (GameState.state == GameState.PLAY) {
            GamePanel.snake.update();
        }
    }
}