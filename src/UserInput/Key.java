package UserInput;

import Main.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Key is a KeyListener that controls the Snake's direction using WASD keys.
 */
public class Key implements KeyListener {

    /**
     * Invoked when a key has been typed.
     * (Not used in this implementation.)
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // No action
    }

    /**
     * Invoked when a key is pressed down.
     * Changes the Snake's direction based on WASD.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                GamePanel.snake.isTurning = true;
                GamePanel.snake.changeHeadDirection(0);
            }
            case KeyEvent.VK_D -> {
                GamePanel.snake.isTurning = true;
                GamePanel.snake.changeHeadDirection(2);
            }
            case KeyEvent.VK_S -> {
                GamePanel.snake.isTurning = true;
                GamePanel.snake.changeHeadDirection(1);
            }
            case KeyEvent.VK_A -> {
                GamePanel.snake.isTurning = true;
                GamePanel.snake.changeHeadDirection(3);
            }
        }
    }

    /**
     * Invoked when a key is released.
     * (Not used in this implementation.)
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // No action
    }
}
