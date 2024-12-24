package GameObject;
import java.awt.image.BufferedImage;

/**
 * The {@code SnakeBody} class represents the body segment of a snake in the game.
 */
public class SnakeBody extends EntityType{
    static BufferedImage whole_snake = Snake.whole_snake;
    static int tileLength = whole_snake.getHeight()/4;

    public static BufferedImage rightNDown, upNLeft;
    public static BufferedImage rightNUp, downNLeft;
    public static BufferedImage leftNDown, upNRight;
    public static BufferedImage leftNUp, downNRight;

    /**
     * Constructs a {@code SnakeBody} object with the specified direction and position.
     *
     * @param direction the initial direction of the snake's body
     * @param xPos the x-coordinate of the snake's body
     * @param yPos the y-coordinate of the snake's body
     */
    public SnakeBody(int direction, int xPos, int yPos){
        super(direction, xPos, yPos);
        this.north = whole_snake.getSubimage(tileLength*2,tileLength,tileLength,tileLength);
        this.south = whole_snake.getSubimage(tileLength*2,tileLength,tileLength,tileLength);
        this.east = whole_snake.getSubimage(tileLength,0,tileLength,tileLength);
        this.west = whole_snake.getSubimage(tileLength,0,tileLength,tileLength);

        //turns
        rightNDown = whole_snake.getSubimage(tileLength*2, 0,tileLength,tileLength);
        upNLeft = rightNDown;
        rightNUp = whole_snake.getSubimage(tileLength*2,tileLength*2,tileLength,tileLength);
        downNLeft = rightNUp;
        leftNDown = whole_snake.getSubimage(0,0,tileLength,tileLength);
        upNRight = leftNDown;
        leftNUp = whole_snake.getSubimage(0,tileLength,tileLength,tileLength);
        downNRight = leftNUp;
    }

    /**
     * Constructs a {@code SnakeBody} object with the specified direction, position, and turning point index.
     *
     * @param direction the initial direction of the snake's body
     * @param xPos the x-coordinate of the snake's body
     * @param yPos the y-coordinate of the snake's body
     * @param turningPointIndex the index of the turning point in the snake's path
     */
    public SnakeBody(int direction, int xPos, int yPos, int turningPointIndex){
        super(direction, xPos, yPos);
        this.north = whole_snake.getSubimage(tileLength*2,tileLength,tileLength,tileLength);
        this.south = whole_snake.getSubimage(tileLength*2,tileLength,tileLength,tileLength);
        this.east = whole_snake.getSubimage(tileLength,0,tileLength,tileLength);
        this.west = whole_snake.getSubimage(tileLength,0,tileLength,tileLength);
        this.turningPointIndex = turningPointIndex;

        rightNDown = whole_snake.getSubimage(tileLength*2, 0,tileLength,tileLength);
        upNLeft = rightNDown;
        rightNUp = whole_snake.getSubimage(tileLength*2,tileLength*2,tileLength,tileLength);
        downNLeft = rightNUp;
        leftNDown = whole_snake.getSubimage(0,0,tileLength,tileLength);
        upNRight = leftNDown;
        leftNUp = whole_snake.getSubimage(0,tileLength,tileLength,tileLength);
        downNRight = leftNUp;
    }
    /**
     * Returns a string representation of the snake's body.
     *
     * @return the string representation of the snake's body
     */
    public String toString(){
        return "Body";
    }
}
