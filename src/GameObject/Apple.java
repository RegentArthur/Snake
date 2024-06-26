package GameObject;

import Utilz.Images;
import Main.GamePanel;

import static Utilz.Constants.APPLE;

public class Apple extends EntityType {

    public Apple(){
       img = Images.getImage(APPLE);
       width = img.getWidth();
       height = img.getHeight();
       position[0] = 13*GamePanel.TILES_LENGTH;
       position[1] = 8* GamePanel.TILES_LENGTH;
    }
    //see GamePanel for explanation
    public void setPosition(){
        do {
            position[0] = (int) (Math.random() * (GamePanel.TILES_PER_ROW - 2) + 1) * GamePanel.TILES_LENGTH;
            position[1] = (int) (Math.random() * (GamePanel.TILES_PER_COL - 4) + 1) * GamePanel.TILES_LENGTH;
        } while (GamePanel.snake.checkCollision(this));
    }


}
