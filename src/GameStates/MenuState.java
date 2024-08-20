package GameStates;

import Main.Game;
import Utilz.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.MENU_UI;

public class MenuState extends JPanel implements StateMethods{

    private BufferedImage background;
    private static final double SCALE = 0.75;
    public static final int X_DISPLACEMENT = 75, Y_DISPLACEMENT = 50;
    public static final double WIDTH_IMG = Game.gameWindow.frame.getWidth()*SCALE;
    public static final double HEIGHT_IMG = Game.gameWindow.frame.getHeight()*SCALE;

    public MenuState(){
        background = Images.getImage(MENU_UI);
    }

    //TODO: since this background of menu buttons is static, is it possible to not use render? and instead just draw it once
    //TODO: this means that the only thing that has to continuously update is the location of the mouse
    @Override
    public void render(Graphics g) {
        /*
        g.drawImage(background, X_DISPLACEMENT,Y_DISPLACEMENT,
                (int) WIDTH_IMG,(int) HEIGHT_IMG, null);


        //g.drawRect(X_DISPLACEMENT,Y_DISPLACEMENT,(int)WIDTH_IMG,(int)HEIGHT_IMG);

        //checking button location
        g.setColor(Color.RED);
        g.drawRect(X_DISPLACEMENT,Y_DISPLACEMENT,(int)Game.menuInterface.dimension[0],(int)Game.menuInterface.dimension[1]);

        //i don't understand why this is offsetted from the 3rd button
        //there doesnt seem to be anything wrong with the height (dimension[1])
        //so the issue likely lies in the Y_DISPLACEMENT...but honestly nothing seems wrong with it either
        g.drawRect(X_DISPLACEMENT,(int)(2*Game.menuInterface.dimension[1]+50),(int)Game.menuInterface.dimension[0],(int)(Game.menuInterface.dimension[1]));

         */
    }

    @Override
    public void update() {

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        System.out.println("hellofiwoiee");
        g.drawImage(background, X_DISPLACEMENT,Y_DISPLACEMENT,
                (int) WIDTH_IMG,(int) HEIGHT_IMG, null);

        //g.drawRect(X_DISPLACEMENT,Y_DISPLACEMENT,(int)WIDTH_IMG,(int)HEIGHT_IMG);

        //checking button location
        g.setColor(Color.RED);
        g.drawRect(X_DISPLACEMENT,Y_DISPLACEMENT,(int)Game.menuInterface.dimension[0],(int)Game.menuInterface.dimension[1]);

        //i don't understand why this is offsetted from the 3rd button
        //there doesnt seem to be anything wrong with the height (dimension[1])
        //so the issue likely lies in the Y_DISPLACEMENT...but honestly nothing seems wrong with it either
        g.drawRect(X_DISPLACEMENT,(int)(2*Game.menuInterface.dimension[1]+50),(int)Game.menuInterface.dimension[0],(int)(Game.menuInterface.dimension[1]));
    }

}
