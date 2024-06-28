package GameObject;

import java.awt.image.BufferedImage;

public class SnakeBody extends EntityType{
    static BufferedImage whole_snake = Snake.whole_snake;
    static int tileLength = whole_snake.getHeight()/4;

    public static BufferedImage rightNDown, upNLeft;
    public static BufferedImage rightNUp, downNLeft;
    public static BufferedImage leftNDown, upNRight;
    public static BufferedImage leftNUp, downNRight;

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
    public String toString(){
        return "Body";
    }
}
