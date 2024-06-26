package Main;

import GameObject.Apple;
import GameObject.Snake;
import UserInput.Key;
import Utilz.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.GRASS;

public class GamePanel extends JPanel {

    private BufferedImage background;

    public static final int TILES_LENGTH = 33;
    public static final int TILES_PER_ROW = 15;
    public static final int TILES_PER_COL = 17;

    //graphics dont display the right number of tiles tho,
    //im wondering if the problem is in how we declare the sizing -- im also unsure of the difference between setSize & setPreferredSize
    //ok wait, i think im just getting confusing between row & col -- i'll check later whatever
    public static final int GAME_WIDTH = TILES_LENGTH*TILES_PER_ROW;
    public static final int GAME_HEIGHT = TILES_LENGTH*TILES_PER_COL;

    public static Apple apple;
    public static Snake snake;
    private static Key key;

    public GamePanel(){
        setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));

        background = Images.getImage(GRASS);
        apple = new Apple();
        snake = new Snake();
        key = new Key();
        addKeyListener(key);

        this.setBorder(BorderFactory.createLineBorder(new Color(18, 21, 28), 33));

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawLevel(g);
        apple.render(g);
        snake.render(g);
    }

    private void drawLevel(Graphics g){
        for(int r=0; r<TILES_PER_COL; r++){
            for(int c=0; c<TILES_PER_ROW; c++){
                //green background:
                //g.drawImage(background,c*TILES_LENGTH,r*TILES_LENGTH,TILES_LENGTH,TILES_LENGTH,null);

                //red background:
                if (r%2 != c%2) {
                    g.setColor(new Color(102, 27, 28));
                }else{
                    g.setColor(new Color(83, 21, 22));
                }
                g.fillRect(c*TILES_LENGTH,r*TILES_LENGTH,TILES_LENGTH,TILES_LENGTH);
            }
        }
    }
}
