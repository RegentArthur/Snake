package GameObject;

import Main.GamePanel;
import Utilz.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Snake extends EntityType {

    private int numBodyParts;
    private int[] START_POS = {6,3};
    protected BufferedImage northFace;
    protected BufferedImage eastFace;
    protected BufferedImage southFace;
    protected BufferedImage westFace;
    protected static BufferedImage whole_snake;
    protected int direction;
    private ArrayList<EntityType> body;
    private int length;

    public Snake(){
        numBodyParts = 0;
        body = new ArrayList<>();


        /*
        body = Images.getImage(Constants.Snake.BODY);
        northFace = Images.getImage(Constants.Snake.NORTH_FACE);
        eastFace = Images.getImage(Constants.Snake.EAST_FACE);
        southFace = Images.getImage(Constants.Snake.SOUTH_FACE);
        westFace = Images.getImage(Constants.Snake.WEST_FACE);
         */
        whole_snake = Images.getImage("snake.png");
        startSnake(5);

        position[0] = START_POS[0];
        position[1] = START_POS[1];
    }

    private void eatApple(){
        numBodyParts++;
    }

    @Override
    public void render(Graphics g){
        for(EntityType segment : body) {
            g.drawImage(segment.getSegment(segment.direction),
                    segment.position[0],segment.position[1],
                    GamePanel.TILES_LENGTH,GamePanel.TILES_LENGTH,null);
        }
    }

    public void startSnake(int initialSize){
        body.add(new SnakeHead(2, START_POS[0], START_POS[1]));
        for(int i = 1; i <= initialSize - 2; i++){
            body.add(new SnakeBody(2, START_POS[0] - i, START_POS[1]));
        }
        body.add(new SnakeTail(2, START_POS[0] - initialSize+1, START_POS[1]));
    }



}
