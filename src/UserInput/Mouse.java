package UserInput;

import GameStates.GameState;
import Main.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mouse is a MouseListener that forwards mouse events to the MenuInterface
 * when the game is in MENU state.
 */
public class Mouse implements MouseListener {

    /**
     * Invoked when the mouse button has been clicked (pressed and released).
     * If the current game state is MENU, it forwards the event to the
     * MenuInterface.
     *
     * @param e the MouseEvent representing the click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (GameState.state == GameState.MENU) {
            Game.menuInterface.mouseClicked(e);
        }
    }

    /**
     * Invoked when a mouse button has been pressed.
     * (Not used in this implementation.)
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // No action
    }

    /**
     * Invoked when a mouse button has been released.
     * (Not used in this implementation.)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // No action
    }

    /**
     * Invoked when the mouse enters a component.
     * (Not used in this implementation.)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // No action
    }

    /**
     * Invoked when the mouse exits a component.
     * (Not used in this implementation.)
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // No action
    }
}
