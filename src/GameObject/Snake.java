package GameObject;

import Main.GamePanel;
import Utilz.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Snake extends EntityType {

    private int numBodyParts;
    private int[] START_POS = {6,3};
    protected static BufferedImage whole_snake;
    private ArrayList<EntityType> body;

    public Snake(){
        numBodyParts = 5;
        body = new ArrayList<>();

        whole_snake = Images.getImage("snake.png");
        startSnake(numBodyParts);

        position[0] = START_POS[0];
        position[1] = START_POS[1];
    }

    private void checkIfEatApple(){
        if(body.get(0).position[0] == GamePanel.apple.position[0] &&
                body.get(0).position[1] == GamePanel.apple.position[1]) {
            incrementSnake();
        }
    }

    @Override
    public void render(Graphics g){
        for(EntityType segment : body) {
            g.drawImage(segment.getSegment(segment.direction),
                    segment.position[0],segment.position[1],
                    GamePanel.TILES_LENGTH,GamePanel.TILES_LENGTH,null);
        }
    }

    public void update(){
        checkIfEatApple();
        incrementPos();
    }

    private void startSnake(int size){
        body.add(new SnakeHead(2, START_POS[0], START_POS[1]));
        for(int i = 1; i <= size - 2; i++){
            body.add(new SnakeBody(2, START_POS[0] - i, START_POS[1]));
        }
        body.add(new SnakeTail(2, START_POS[0] - size+1, START_POS[1]));
    }

    private void incrementSnake(){
        numBodyParts++;

        //assumes snake is always travelling right in same yPos, but good enough for now until we add on bends
        int tailXPos = body.get(body.size()-1).position[0];
        int tailYPos = body.get(body.size()-1).position[1];
        body.add(body.size()-1, new SnakeBody(2,tailXPos,tailYPos));
    }

    //assuming only moving x direction for now
    //note: doesnt exactly work yet, need to make it so that snake moves by one block per move
    private void incrementPos(){
        for(EntityType segment : body) {
            segment.position[0]++;
        }
    }

}
