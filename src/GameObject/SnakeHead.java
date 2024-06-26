package GameObject;

import java.awt.image.BufferedImage;

public class SnakeHead extends EntityType{
    static BufferedImage whole_snake = Snake.whole_snake;
    static int tileLength = whole_snake.getHeight()/4;

    public SnakeHead(int direction, int xPos, int yPos){
        super(direction, xPos, yPos);
        this.north = whole_snake.getSubimage(tileLength*3,0,tileLength,tileLength);
        this.south = whole_snake.getSubimage(tileLength*4,tileLength,tileLength,tileLength);
        this.east = whole_snake.getSubimage(tileLength*4,0,tileLength,tileLength);
        this.west = whole_snake.getSubimage(tileLength*3,tileLength,tileLength,tileLength);
    }
}
