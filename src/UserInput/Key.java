package UserInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W -> {
                System.out.println("Up");
            }
            case KeyEvent.VK_D -> {
                System.out.println("Right");
            }
            case KeyEvent.VK_S -> {
                System.out.println("Down");
            }
            case KeyEvent.VK_A -> {
                System.out.println("Left");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
