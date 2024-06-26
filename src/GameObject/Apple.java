package GameObject;

import Utilz.Images;
import Main.GamePanel;

import static Utilz.Constants.APPLE;

public class Apple extends EntityType {

    public Apple(){
       img = Images.getImage(APPLE);
       width = img.getWidth();
       height = img.getHeight();
       setPosition();
       //position[0] = 9*GamePanel.TILES_LENGTH;
       //position[1] = 3* GamePanel.TILES_LENGTH;
    }

    public void setPosition(){
        do {
            position[0] = (int) (Math.random() * GamePanel.TILES_PER_ROW) * GamePanel.TILES_LENGTH;
            position[1] = (int) (Math.random() * GamePanel.TILES_PER_COL) * GamePanel.TILES_LENGTH;
        } while (GamePanel.snake.checkCollision(this));
    }


}
