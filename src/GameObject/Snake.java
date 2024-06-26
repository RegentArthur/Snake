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
        //System.out.println(body.size());
        /*for(int i = 0; i < body.size(); i++) {
            g.drawImage(body.get(i).getSegment(body.get(i).direction),
                    body.get(i).position[0],body.get(i).position[1],
                    GamePanel.TILES_LENGTH,GamePanel.TILES_LENGTH,null);
        }*/
        for(EntityType segment : body) {
            g.drawImage(segment.getSegment(segment.direction),
                    segment.position[0],segment.position[1],
                    GamePanel.TILES_LENGTH,GamePanel.TILES_LENGTH,null);
        }
    }

    public void update(){
        checkIfEatApple();
        if(!checkCollision()){
            incrementPos();
        }
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
        //Snake grows in different directions depending on the direction of the snake tail
        System.out.println(body.getLast().direction);
        switch (body.getLast().direction) {
            case 0 -> body.getLast().position[1] = tailYPos + GamePanel.TILES_LENGTH;
            case 1 -> body.getLast().position[1] = tailYPos - GamePanel.TILES_LENGTH;
            case 2 -> body.getLast().position[0] = tailXPos - GamePanel.TILES_LENGTH;
            case 3 -> body.getLast().position[0] = tailXPos + GamePanel.TILES_LENGTH;
            //default -> throw new IllegalArgumentException("Invalid direction: " + body.getLast().direction);
        }
        System.out.println("1");
        //ToDo: check if this can get wrong because integer division might cause the snake to have segment overlap
        body.add(body.size()-1, new SnakeBody(2,tailXPos/GamePanel.TILES_LENGTH,
                tailYPos/GamePanel.TILES_LENGTH));
        GamePanel.apple.setPosition();
    }

    //assuming only moving x direction for now
    private void incrementPos(){
        for(EntityType segment : body) {
            segment.position[0]++;
        }
    }
    private boolean checkCollision(){
        double headXPos = (double)body.getFirst().position[0]/GamePanel.TILES_LENGTH;
        double headYPos = (double)body.getFirst().position[1]/GamePanel.TILES_LENGTH;
        if(headXPos<1 || headXPos>(GamePanel.TILES_PER_ROW-2) || headYPos<1 || headYPos>(GamePanel.TILES_PER_COL-4)){
            return true;
        }
        return false;
    }
    public <T extends EntityType> boolean checkCollision(T object){
        boolean flag = false;
        for(EntityType segment : body){
            segment.isInSameSquare(object);
        }
        return flag;
    }

}