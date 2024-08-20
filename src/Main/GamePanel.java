package Main;

import Buttons.MenuInterface;
import GameObject.Apple;
import GameObject.Snake;
import UserInput.Key;
import UserInput.Mouse;
import Utilz.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Main.Game.gameWindow;
import static Utilz.Constants.GRASS;

public class GamePanel extends JPanel {

    private BufferedImage background;

    public static final int TILES_LENGTH = 33;
    public static final int TILES_PER_ROW = 19; //For some reason the number of squares is actually this -2
    public static final int TILES_PER_COL = 19; //-4
    public static final int GAME_WIDTH = TILES_LENGTH*TILES_PER_ROW;
    public static final int GAME_HEIGHT = TILES_LENGTH*TILES_PER_COL;

    public static Apple apple;
    public static Snake snake;
    private static Key key;
    private static Mouse mouse;

    public static Game game;

    public GamePanel(Game game){
        setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));

        background = Images.getImage(GRASS);
        snake = new Snake();
        apple = new Apple();
        key = new Key();
        mouse = new Mouse();
        addKeyListener(key);
        addMouseListener(mouse);

        this.game = game;

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    public void drawLevel(Graphics g){

        this.setBorder(BorderFactory.createLineBorder(new Color(18, 21, 28), 33));

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

        gameWindow.topBar.topBar.repaint();

    }
}



