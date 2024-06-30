package Buttons;

import GameStates.GameState;
import GameStates.MenuState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static Utilz.Constants.MENU_UI;

public class MenuInterface extends Button {

    private BufferedImage[] subImg;

    public MenuInterface(){
        super(MENU_UI);
        subImg = new BufferedImage[3];
        location[0] = MenuState.X_DISPLACEMENT;
        location[1] = MenuState.Y_DISPLACEMENT;
        dimension[0] = MenuState.WIDTH_IMG;
        dimension[1] = MenuState.HEIGHT_IMG/3; //ehh, not exact but isok
    }

    /*
    private void getSubImages(){
        for(int i=0; i< subImg.length; i++){
            img.getSubimage(0,(int)(i*dimension[1]),(int)dimension[0],(int)dimension[1]);
        }
    }
     */

    public void mouseClicked(MouseEvent e) {
        getMouseLoc(e);

        //clicked Play button
        if(isWithinXBounds() && (mouseY >= location[1] && mouseY <= dimension[1])){
            GameState.state = GameState.PLAY;
        } else if(isWithinXBounds() && (mouseY >= 2*dimension[1]+50 && mouseY <= 3*dimension[1]+50)){ //clicked Exit button
            //for reasoning around why +50, look at MenuState render()
            System.out.println("Successfully exited game! :)");
            System.exit(0);
        }


    }

    private boolean isWithinXBounds(){
        return mouseX >= location[0] && mouseX <= location[0]+dimension[0];
    }
}
