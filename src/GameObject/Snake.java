package GameObject;

import Main.GamePanel;
import Utilz.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * The {@code Snake} class represents the snake in the game, including its head, body, and tail.
 *
 * <p>This class manages the snake's movement, collisions, rendering, and interactions
 * with game elements such as apples and the game grid.
 */
public class Snake extends EntityType {

    /**
     * A helper class that represents a turning point in the snake's movement.
     *
     * <p>The {@code TurningPoint} class is used to mark where the snake has turned,
     * storing the position and direction of the turn.
     */
    class TurningPoint extends EntityType{ // this is essentially just to serve as a marking of where the snake has turned
        int x;
        int y;

        /**
         * Constructs a {@code TurningPoint} object with the specified position and direction.
         *
         * @param x the x-coordinate of the turning point
         * @param y the y-coordinate of the turning point
         * @param direction the direction at the turning point
         */
        public TurningPoint(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.position[0] = x;
            this.position[1] = y;
        }
    }
    private int numBodyParts;
    private final int[] START_POS = {6,8};
    public boolean isTurning = false;
    protected static BufferedImage whole_snake;
    private LinkedList<EntityType> body;
    private int nextKeyInput = -1;
    private LinkedList<TurningPoint> turningPoints = new LinkedList<>(); // using a queue to represent turns
    private int score;

    /**
     * Constructs a {@code Snake} object and initializes its position, size, and body parts.
     */
    public Snake(){
        numBodyParts = 5;
        body = new LinkedList<>();

        whole_snake = Images.getImage("snake.png");
        startSnake(numBodyParts);
        score = 0;

        position[0] = START_POS[0];
        position[1] = START_POS[1];
    }

    /**
     * Checks if the snake has eaten an apple by comparing the head and apple positions.
     *
     * @return {@code true} if the snake has eaten the apple, {@code false} if otherwise.
     */
    private boolean checkIfEatApple(){
        if(body.getFirst().position[0] == GamePanel.apple.position[0] &&
                body.getFirst().position[1] == GamePanel.apple.position[1]) {
            incrementSnake();
            return true;
        }
        return false;
    }

    /**
     * Renders the snake's body, head, and tail on the screen.
     *
     * @param g the {@link Graphics} object used for drawing
     */
    @Override
    public void render(Graphics g){
        for(int i = 0; i<body.size();i++) {
            EntityType segment = body.get(i);
            if(segment != body.getFirst() && segment.turningPointIndex < turningPoints.size() &&
                    turningPoints.get(segment.turningPointIndex).isInSameSquare(segment) ){
                int turnPosX = turningPoints.get(segment.turningPointIndex).position[0];
                int turnPosY = turningPoints.get(segment.turningPointIndex).position[1];
                g.drawImage(getSegmentIfTurning(segment,segment.direction, turningPoints.get(segment.turningPointIndex)),
                        turnPosX, turnPosY, GamePanel.TILES_LENGTH, GamePanel.TILES_LENGTH, null);
                if(segment != body.getLast() ){
                    if(
                            !(segment.turningPointIndex == 0)&&
                            turningPoints.get(segment.turningPointIndex-1).isInSameSquare(body.get(i+1))
                    ){
                        continue;
                    }
                    switch (segment.direction) {
                        case 0 -> turnPosY = turnPosY + GamePanel.TILES_LENGTH;
                        case 1 -> turnPosY = turnPosY - GamePanel.TILES_LENGTH;
                        case 2 -> turnPosX = turnPosX - GamePanel.TILES_LENGTH;
                        case 3 -> turnPosX = turnPosX + GamePanel.TILES_LENGTH;
                        default -> throw new IllegalArgumentException("Invalid direction: " + body.getLast().direction);
                    }
                    g.drawImage(segment.getSegment(segment.direction), turnPosX, turnPosY, GamePanel.TILES_LENGTH,
                            GamePanel.TILES_LENGTH, null);
                }
            } else{
                g.drawImage(segment.getSegment(segment.direction),
                        segment.position[0], segment.position[1],
                        GamePanel.TILES_LENGTH, GamePanel.TILES_LENGTH, null);

            }
        }
    }

    /**
     * Updates the snake's position, handles collisions, and checks interactions with game elements.
     */
    public void update(){
        checkIfEatApple();
        checkTurns();
        if (!checkCollision() && !checkCollisionWithHead()){
            incrementPos();
        } else {
            // Handle game over
            System.out.println("The game is over! Your snake has eaten " + score + " apples!");
            System.out.println("Thank you for playing our snake game!");
            System.out.println("The window will close im 10 seconds.");
            try {
                Thread.sleep(10000); // Sleep for 10 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread was interrupted while sleeping: " + e.getMessage());
            }
            System.exit(1);
        }
    }

    /**
     * Checks and processes the snake's turning points, updating body segments if
     * they are ready to make a turn.
     */
    public void checkTurns(){
        if(isHeadAlignedWithGrid() && nextKeyInput != -1){
            body.getFirst().direction = nextKeyInput;
            turningPoints.add(new TurningPoint(body.getFirst().position[0], body.getFirst().position[1],
                    body.getFirst().direction));
            nextKeyInput = -1;
        }
        // Update the body segments
        for (int i = 1; i < body.size(); i++) {
            EntityType segment = body.get(i);
            // segment.turningPointIndex is the number of turns that the segment has completed
            // turningPoints.size() is the number of turns that the snake has done currently
            if (segment.turningPointIndex < turningPoints.size()) {
                TurningPoint tp = turningPoints.get(segment.turningPointIndex);
                if (segment.position[0] == tp.x && segment.position[1] == tp.y) {
                    segment.direction = tp.direction;
                    segment.turningPointIndex++;
                }
            }
        }

        // Remove turning points that have been processed by all segments
        // If the tail segment has completed a turn, that means that the snake is past that turning point,
        // which is the first one in the queue
        if (!turningPoints.isEmpty() && body.getLast().turningPointIndex > 0) {
            turningPoints.poll();
            for (EntityType segment : body) {
                segment.turningPointIndex--;
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

    /**
     * Increments the size of the snake and attaches the correct facing body part
     * to the end of the snake. It also updates the score.
     */
    private void incrementSnake(){
        numBodyParts++;
        score++;
        int[] tailPos = body.getLast().position;
        int tailXPos = tailPos[0];
        int tailYPos = tailPos[1];
        //Snake grows in different directions depending on the direction of the snake tail
        switch (body.getLast().direction) {
            case 0 -> body.getLast().position[1] = tailYPos + GamePanel.TILES_LENGTH;
            case 1 -> body.getLast().position[1] = tailYPos - GamePanel.TILES_LENGTH;
            case 2 -> body.getLast().position[0] = tailXPos - GamePanel.TILES_LENGTH;
            case 3 -> body.getLast().position[0] = tailXPos + GamePanel.TILES_LENGTH;
            default -> throw new IllegalArgumentException("Invalid direction: " + body.getLast().direction);
        }
        body.add(body.size()-1, new SnakeBody(body.getLast().direction,tailXPos/GamePanel.TILES_LENGTH,
                tailYPos/GamePanel.TILES_LENGTH, body.getLast().turningPointIndex));
        GamePanel.apple.setPosition();
    }

    /**
     * Increments the position of each segment of the snake based on its direction.
     */
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

    /**
     * Checks whether the snake's head has collided with the game boundaries.
     *
     * @return {@code true} if the snake has collided, {@code false} otherwise
     */
    private boolean checkCollision(){
        double headXPos = (double)body.getFirst().position[0]/GamePanel.TILES_LENGTH;
        double headYPos = (double)body.getFirst().position[1]/GamePanel.TILES_LENGTH;
        if(headXPos<1 || headXPos>(GamePanel.TILES_PER_ROW-2) || headYPos<1 || headYPos>(GamePanel.TILES_PER_COL-2)){
            return true;
        }
        return false;
    }

    /**
     * Checks whether the snake's head has collided with another {@code EntityType} object.
     *
     * @param object the object that we are checking for a collision with
     * @return {@code true} if the head has collided with the body, {@code false} otherwise
     */
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

    /**
     * Changes the direction of the snake's head based on player input.
     *
     * @param direction the new direction for the snake's head
     */
    public void changeHeadDirection(int direction){
        if(!(Math.abs(body.getFirst().direction - direction) == 1) ||
                (body.getFirst().direction == 1 && direction==2) || (body.getFirst().direction == 2 && direction==1)){
            nextKeyInput = direction;
        }
    }

    /**
     * Determines if the snake's head is aligned with the game grid.
     *
     * @return {@code true} if the head is aligned, {@code false} otherwise
     */

    private boolean isHeadAlignedWithGrid() {
        return (body.getFirst().position[0] % GamePanel.TILES_LENGTH == 0) && (body.getFirst().position[1] %
                GamePanel.TILES_LENGTH == 0);
    }

    /**
     * Returns the appropriate sprite for a segment when it is turning.
     *
     * @param segment the current segment
     * @param direction the segment's direction
     * @param turningPoint the turning point for the segment
     * @return the {@link BufferedImage} representing the turning segment
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

    /**
     * Returns the current score of the game.
     *
     * @return the score as an integer
     */
    public int getScore(){
        return score;
    }
}