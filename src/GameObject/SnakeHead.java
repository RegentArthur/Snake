package GameObject;

import java.awt.image.BufferedImage;

/**
 * The {@code SnakeHead} class represents the head segment of a snake in the game.
 */
public class SnakeHead extends EntityType{
    static BufferedImage whole_snake = Snake.whole_snake;
    static int tileLength = whole_snake.getHeight()/4;

    /**
     * Constructs a {@code SnakeHead} object with the specified direction and position.
     *
     * @param direction the initial direction of the snake's head
     * @param xPos the x-coordinate of the snake's head
     * @param yPos the y-coordinate of the snake's head
     */
    public SnakeHead(int direction, int xPos, int yPos){
        super(direction, xPos, yPos);
        this.north = whole_snake.getSubimage(tileLength*3,0,tileLength,tileLength);
        this.south = whole_snake.getSubimage(tileLength*4,tileLength,tileLength,tileLength);
        this.east = whole_snake.getSubimage(tileLength*4,0,tileLength,tileLength);
        this.west = whole_snake.getSubimage(tileLength*3,tileLength,tileLength,tileLength);
    }

    /**
     * Returns a string representation of the snake's head.
     *
     * @return the string representation of the snake's head
     */
    public String toString(){
        return "Head";
    }
}
