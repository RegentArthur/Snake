package GameObject;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    public BufferedImage img;
    public int[] position = new int[2];
    public int width, height;

    public void render(Graphics g){
        g.drawImage(img, position[0], position[1], GamePanel.TILES_LENGTH, GamePanel.TILES_LENGTH, null);
    }

}
