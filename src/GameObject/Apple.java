package GameObject;

import Utilz.Images;
import Main.GamePanel;

import static Utilz.Constants.APPLE;

public class Apple extends Entity{

    public Apple(){
       img = Images.getImage(APPLE);
       width = img.getWidth();
       height = img.getHeight();
       setPosition();
    }

    private void setPosition(){
        position[0] = (int)(Math.random()*GamePanel.TILES_PER_ROW)*GamePanel.TILES_LENGTH;
        position[1] = (int)(Math.random()*GamePanel.TILES_PER_COL)*GamePanel.TILES_LENGTH;
    }

}
