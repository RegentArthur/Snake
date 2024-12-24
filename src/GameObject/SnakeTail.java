package GameObject;

import java.awt.image.BufferedImage;

/**
 * The {@code SnakeTail} class represents the tail segment of a snake in the game.
 *
 * <p>This class extends {@link EntityType} and provides a specific implementation
 * for the snake's tail, including initializing its graphical representation
 * based on its direction and position.
 */
public class SnakeTail extends EntityType{
    static BufferedImage whole_snake = Snake.whole_snake;
    static int tileLength = whole_snake.getHeight()/4;

    /**
     * Constructs a {@code SnakeTail} object with the specified direction and position.
     *
     * <p>This constructor sets up the graphical assets for the tail by extracting
     * the appropriate sub-images from the snake's sprite sheet based on direction.
     *
     * @param direction the initial direction of the snake's tail
     * @param xPos the x-coordinate of the snake's tail
     * @param yPos the y-coordinate of the snake's tail
     */
    public SnakeTail(int direction, int xPos, int yPos){
        super(direction, xPos, yPos);
        this.north = whole_snake.getSubimage(tileLength*3,tileLength*2,tileLength,tileLength);
        this.south = whole_snake.getSubimage(tileLength*4,tileLength*3,tileLength,tileLength);
        this.east = whole_snake.getSubimage(tileLength*4,tileLength*2,tileLength,tileLength);
        this.west = whole_snake.getSubimage(tileLength*3,tileLength*3,tileLength,tileLength);
    }

    /**
     * Returns a string representation of the snake's tail.
     *
     * @return the string representation of the snake's tail
     */
    @Override
    public String toString() {
        return "Tail";
    }
}
