package GameStates;

import java.awt.*;

/**
 * The {@code StateMethods} interface defines the contract for state-based behavior
 * in applications, particularly for rendering and updating visual or logical states.
 *
 * <p>Implementing classes are expected to provide specific behavior for rendering
 * the state on a graphical interface and updating the state as needed.
 */
public interface StateMethods {
    public void render(Graphics g);
    public void update();
}
