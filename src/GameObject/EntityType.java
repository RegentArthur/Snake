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
        switch (direction){
            case 0: return north;
            case 1: return south;
            case 2: return east;
            case 3: return west;
        }
        return null;
    }

}
