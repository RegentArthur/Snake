package GameObject;

import Utilz.Images;
import Main.GamePanel;
import static Utilz.Constants.APPLE;

/**
 * The {@code Apple} class represents the apple in the game, which the snake can consume to grow and score points.
 */
public class Apple extends EntityType {

    /**
     * Constructs an {@code Apple} object and initializes its position and image.
     *
     * <p>The apple is initially placed at a default position on the game grid.
     */
    public Apple(){
       img = Images.getImage(APPLE);
       width = img.getWidth();
       height = img.getHeight();
       position[0] = 13*GamePanel.TILES_LENGTH;
       position[1] = 8* GamePanel.TILES_LENGTH;
    }

    /**
     * Sets a new position for the apple on the game grid.
     *
     * <p>The new position is randomly generated within the bounds of the grid and
     * ensures that it does not overlap with the snake's current position.
     */
    public void setPosition(){
        do {
            position[0] = (int) (Math.random() * (GamePanel.TILES_PER_ROW - 2) + 1) * GamePanel.TILES_LENGTH;
            position[1] = (int) (Math.random() * (GamePanel.TILES_PER_COL - 4) + 1) * GamePanel.TILES_LENGTH;
        } while (GamePanel.snake.checkCollision(this));
    }
}
