package GameObject;

import java.awt.image.BufferedImage;

public class SnakeBody extends EntityType{
    static BufferedImage whole_snake = Snake.whole_snake;
    static int tileLength = whole_snake.getHeight()/4;

    public SnakeBody(int direction, int xPos, int yPos){
        super(direction, xPos, yPos);
        this.north = whole_snake.getSubimage(tileLength*2,tileLength,tileLength,tileLength);
        this.south = whole_snake.getSubimage(tileLength*2,tileLength,tileLength,tileLength);
        this.east = whole_snake.getSubimage(tileLength,0,tileLength,tileLength);
        this.west = whole_snake.getSubimage(tileLength,0,tileLength,tileLength);

    }
    public String toString(){
        return "Body";
    }
}
