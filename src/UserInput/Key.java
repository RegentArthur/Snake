package UserInput;

import Main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                GamePanel.snake.changeHeadDirection(0);
            }
            case KeyEvent.VK_D -> {
                GamePanel.snake.changeHeadDirection(2);
            }
            case KeyEvent.VK_S -> {
                GamePanel.snake.changeHeadDirection(1);
            }
            case KeyEvent.VK_A -> {
                GamePanel.snake.changeHeadDirection(3);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
