package GameObject;

import Main.GamePanel;
import Utilz.Images;

import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class Snake extends EntityType {
    class TurningPoint extends EntityType{ // this is essentially just to serve as a marking of where the snake has turned
        int x;
        int y;
        public TurningPoint(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.position[0] = x;
            this.position[1] = y;
        }
    }
    private int numBodyParts;
    private int[] START_POS = {6,8};
    public boolean isTurning = false;
    protected static BufferedImage whole_snake;
    private LinkedList<EntityType> body;   //It would actually make more sense to use a LinkedList here, but its ok
    private int nextKeyInput = -1;
    private LinkedList<TurningPoint> turningPoints = new LinkedList<>();
    private int test = 0;
    public int score = 0;
    public Snake(){
        numBodyParts = 5;
        body = new LinkedList<>();

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
        for(int i = 0; i<body.size();i++) {
            EntityType segment = body.get(i);
            if(segment != body.getFirst() && segment.turningPointIndex < turningPoints.size() && turningPoints.get(segment.turningPointIndex).isInSameSquare(segment) ){
                //System.out.println(test);
                //test++;
                int turnPosX = turningPoints.get(segment.turningPointIndex).position[0];
                int turnPosY = turningPoints.get(segment.turningPointIndex).position[1];
                g.drawImage(getSegmentIfTurning(segment,segment.direction, turningPoints.get(segment.turningPointIndex)),
                        turnPosX, turnPosY, GamePanel.TILES_LENGTH, GamePanel.TILES_LENGTH, null);
                //ToDo: still need to figure out a way to make this look smooth, this fixes some things, but it def has its issues
                if(segment != body.getLast() ){
                    if(!(segment.turningPointIndex == 0)&&turningPoints.get(segment.turningPointIndex-1).isInSameSquare(body.get(i+1))){
                        continue;
                    }
                    switch (segment.direction) {
                        case 0 -> turnPosY = turnPosY + GamePanel.TILES_LENGTH;
                        case 1 -> turnPosY = turnPosY - GamePanel.TILES_LENGTH;
                        case 2 -> turnPosX = turnPosX - GamePanel.TILES_LENGTH;
                        case 3 -> turnPosX = turnPosX + GamePanel.TILES_LENGTH;
                        //default -> throw new IllegalArgumentException("Invalid direction: " + body.getLast().direction);
                    }
                    g.drawImage(segment.getSegment(segment.direction), turnPosX, turnPosY, GamePanel.TILES_LENGTH, GamePanel.TILES_LENGTH, null);
                }
            } else{
                g.drawImage(segment.getSegment(segment.direction),
                        segment.position[0], segment.position[1],
                        GamePanel.TILES_LENGTH, GamePanel.TILES_LENGTH, null);

            }
        }
        /*if(segment != body.getFirst() && segment.turningPointIndex < turningPoints.size() && segment.isInSameSquare(turningPoints.get(segment.turningPointIndex)) ) {
            g.drawImage(getSegmentIfTurning())
        }
        for(TurningPoint turn : turningPoints) {

        } */


    }

    public void update(){
        checkIfEatApple();
        //this movement doesn't feel as snappy as the original game, so we should probably tweak this later
        checkTurns();
        //ToDo: the self-collision doesnt work, ill get to it later
        if(!checkCollision() && !checkCollisionWithHead()){
            incrementPos();
        }
    }

    // Another way we could do this method is just by using a hashmap of turning points with location
    // as the dictionary, and that will probably be faster. Oh and you wouldn't need the turningPointIndex
    // complicated stuff too, yea I shoulda just done that, ill switch it over tmrw
    // well, idk yet actually, the linked list might work better for implementing the bent pngs, although I kinda doubt it
    // ToDo: for practice, change this logic such that it implements a hashMap instead
    public void checkTurns(){
        if(isHeadAlignedWithGrid() && nextKeyInput != -1){
            body.getFirst().direction = nextKeyInput;
            turningPoints.add(new TurningPoint(body.getFirst().position[0], body.getFirst().position[1], body.getFirst().direction));
            nextKeyInput = -1;
        }
        //System.out.println(turningPoints.size()); // testing purposes, seeing if they're the right value
        // Update the body segments
        for (int i = 1; i < body.size(); i++) {
            EntityType segment = body.get(i);
            // segment.turningPointIndex is the number of turns that the segment has completed
            // turningPoints.size() is the number of turns that the snake has done currently
            if (segment.turningPointIndex < turningPoints.size()) { // Check if the segment has more turning points to get to
                TurningPoint tp = turningPoints.get(segment.turningPointIndex);
                if (segment.position[0] == tp.x && segment.position[1] == tp.y) { // Check if it is the right position to turn
                    segment.direction = tp.direction;
                    segment.turningPointIndex++; // mark that the segment has completed another turn
                }
            }
            //System.out.print(segment.turningPointIndex);
        }
        //System.out.println();

        // Remove turning points that have been processed by all segments
        // If the tail segment has completed a turn, that means that the snake is past that turning point,
        // which is the first one in the linked list
        if (!turningPoints.isEmpty() && body.getLast().turningPointIndex > 0) {
            turningPoints.poll(); // removes the first item in the linked list
            for (EntityType segment : body) {
                segment.turningPointIndex--; // Adjust the index for all segments
            }
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
        score++;
        int tailXPos = body.get(body.size()-1).position[0];
        int tailYPos = body.get(body.size()-1).position[1];
        //System.out.println(body.getLast().direction);
        //Snake grows in different directions depending on the direction of the snake tail
        switch (body.getLast().direction) {
            case 0 -> body.getLast().position[1] = tailYPos + GamePanel.TILES_LENGTH;
            case 1 -> body.getLast().position[1] = tailYPos - GamePanel.TILES_LENGTH;
            case 2 -> body.getLast().position[0] = tailXPos - GamePanel.TILES_LENGTH;
            case 3 -> body.getLast().position[0] = tailXPos + GamePanel.TILES_LENGTH;
            //default -> throw new IllegalArgumentException("Invalid direction: " + body.getLast().direction);
        }
        //ToDo: check if this can get wrong because integer division might cause the snake to have segment overlap
        // but its lowkey fine tho
        body.add(body.size()-1, new SnakeBody(body.getLast().direction,tailXPos/GamePanel.TILES_LENGTH,
                tailYPos/GamePanel.TILES_LENGTH, body.getLast().turningPointIndex));
        GamePanel.apple.setPosition();


    }

    private void incrementPos(){
        for(EntityType segment : body) {
            switch (segment.direction){
                case 0 -> segment.position[1]--;
                case 1 -> segment.position[1]++;
                case 2 -> segment.position[0]++;
                case 3 -> segment.position[0]--;
            }
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
            if(segment != object) {
                return segment.isInSameSquare(object);
            }
        }
        return flag;
    }

    public boolean checkCollisionWithHead(){
        for(int i=2; i<body.size(); i++){ //start w/2nd segment cuz head can never collide with 1st segment
            if(body.get(i).isInSameSquare(body.getFirst())){
                return true;
            }
        }
        return false;
    }


    public void changeHeadDirection(int direction){
        if(!(Math.abs(body.getFirst().direction - direction) == 1) ||
                (body.getFirst().direction == 1 && direction==2) || (body.getFirst().direction == 2 && direction==1)){
            nextKeyInput = direction;
        }
    }
    private boolean isHeadAlignedWithGrid() {
        return (body.getFirst().position[0] % GamePanel.TILES_LENGTH == 0) && (body.getFirst().position[1] % GamePanel.TILES_LENGTH == 0);
    }

    /*
    public BufferedImage rightNDown, upNLeft;
    public BufferedImage rightNUp, downNLeft;
    public BufferedImage leftNDown, upNRight;
    public BufferedImage leftNUp, downNRight;

    // 0: North, 1: South, 2: East, 3: West
     */


    public BufferedImage getSegmentIfTurning(EntityType segment, int direction, EntityType turningPoint){
        int dirHead = turningPoint.direction;

        int dirSegment = segment.direction;

        if((dirSegment == 2 && dirHead == 1) || (dirSegment == 0 && dirHead == 3)){
            return SnakeBody.rightNDown;
        }else if((dirSegment == 2 && dirHead == 0) || (dirSegment == 1 && dirHead == 3)){
            return SnakeBody.rightNUp;
        }else if((dirSegment == 3 && dirHead == 1) || (dirSegment == 0 && dirHead == 2)){
            return SnakeBody.leftNDown;
        }else if((dirSegment == 3 && dirHead == 0) || (dirSegment == 1 && dirHead == 2)) {
            return SnakeBody.leftNUp;
        }else{
            return segment.getSegment(direction);
        }

    }

}