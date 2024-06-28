package GameObject;

import Main.GamePanel;

import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class EntityType {

    public BufferedImage img;
    public int[] position = new int[2]; // 0: X, 1: Y,  start counting with 1
    public int width, height;
    public int direction; // 0: North, 1: South, 2: East, 3: West
    public BufferedImage north;
    public BufferedImage south;
    public BufferedImage east;
    public BufferedImage west;
    public int turningPointIndex = 0;

    public void render(Graphics g){
        g.drawImage(img, position[0], position[1], GamePanel.TILES_LENGTH, GamePanel.TILES_LENGTH, null);
    }

    public EntityType(){}
    public EntityType(int direction, int xPos, int yPos){
        this.direction = direction;
        this.position[0] = xPos*GamePanel.TILES_LENGTH;
        this.position[1] = yPos*GamePanel.TILES_LENGTH;
    }

    public BufferedImage getSegment(int direction){
        return switch (direction){
            case 0 -> north;
            case 1 -> south;
            case 2 -> east;
            case 3 -> west;
            default -> throw new IllegalArgumentException("Invalid direction index: " + direction);
        };

    }




    public <T extends EntityType> boolean isInSameSquare(T object){
        double x1 = Math.ceil((double)this.position[0]/ GamePanel.TILES_LENGTH);
        double y1 = Math.ceil((double)this.position[1]/ GamePanel.TILES_LENGTH);
        double x2 = Math.ceil((double)object.position[0]/ GamePanel.TILES_LENGTH);
        double y2 = Math.ceil((double)object.position[1]/ GamePanel.TILES_LENGTH);
        return x1==x2 && y1==y2;
    }
}
