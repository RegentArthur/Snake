package GameObject;

import Main.GamePanel;
import Utilz.Constants;
import Utilz.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Snake extends Entity{

    private int numBodyParts;
    private int[] START_POS = {5*GamePanel.TILES_LENGTH,3*GamePanel.TILES_LENGTH};

    private BufferedImage body;
    private BufferedImage northFace;
    private BufferedImage eastFace;
    private BufferedImage southFace;
    private BufferedImage westFace;
    private BufferedImage whole_snake;

    public Snake(){
        numBodyParts = 0;

        /*
        body = Images.getImage(Constants.Snake.BODY);
        northFace = Images.getImage(Constants.Snake.NORTH_FACE);
        eastFace = Images.getImage(Constants.Snake.EAST_FACE);
        southFace = Images.getImage(Constants.Snake.SOUTH_FACE);
        westFace = Images.getImage(Constants.Snake.WEST_FACE);
         */
        whole_snake = Images.getImage("snake.png");
        getSubImages();

        position[0] = START_POS[0];
        position[1] = START_POS[1];
    }

    private void eatApple(){
        numBodyParts++;
    }

    @Override
    public void render(Graphics g){
        g.drawImage(eastFace,position[0],position[1],GamePanel.TILES_LENGTH,GamePanel.TILES_LENGTH,null);
    }

    private void getSubImages(){
        int tileLength = whole_snake.getHeight()/4;
        northFace = whole_snake.getSubimage(tileLength*3,0,tileLength,tileLength);
        eastFace = whole_snake.getSubimage(tileLength*4,0,tileLength,tileLength);
        southFace = whole_snake.getSubimage(tileLength*4,tileLength*1,tileLength,tileLength);
        westFace = whole_snake.getSubimage(tileLength*3,tileLength*1,tileLength,tileLength);
    }

}
