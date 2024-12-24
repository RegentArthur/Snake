package Buttons;

import GameStates.GameState;
import GameStates.MenuState;

import java.awt.event.MouseEvent;

import static Utilz.Constants.MENU_UI;

/**
 * The {@code MenuInterface} class represents the interactive menu interface for the game.
 *
 * <p>This class manages menu-related interactions, such as handling clicks on the "Play"
 * and "Exit" buttons, and defines the layout and behavior of the menu.
 */
public class MenuInterface extends Button {

    /**
     * Constructs a {@code MenuInterface} object and initializes its position, dimensions,
     * and button states for rendering.
     */
    public MenuInterface(){
        super(MENU_UI);
        location[0] = MenuState.X_DISPLACEMENT;
        location[1] = MenuState.Y_DISPLACEMENT;
        dimension[0] = MenuState.WIDTH_IMG;
        dimension[1] = MenuState.HEIGHT_IMG/3; //ehh, not exact but isok
    }

    /**
     * Handles mouse click events and triggers the appropriate actions based on the button clicked.
     *
     * <p>If the "Play" button is clicked, the game transitions to the {@code PLAY} state.
     * If the "Exit" button is clicked, the application exits.
     *
     * @param e the {@link MouseEvent} representing the mouse click
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

    /**
     * Checks if the current mouse position is within the horizontal bounds of the menu interface.
     *
     * @return {@code true} if the mouse is within the x-bounds, {@code false} otherwise
     */
    private boolean isWithinXBounds(){
        return mouseX >= location[0] && mouseX <= location[0]+dimension[0];
    }
}
