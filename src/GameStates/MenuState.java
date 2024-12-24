package GameStates;

import Main.Game;
import Utilz.Images;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static Utilz.Constants.MENU_UI;

/**
 * Represents the game's menu state. Displays a background image and defines
 * areas for menu buttons. Implements StateMethods to fit into a broader
 * state-driven design.
 */
public class MenuState extends JPanel implements StateMethods {

    private BufferedImage background;
    private static final double SCALE = 0.75;
    public static final int X_DISPLACEMENT = 75;
    public static final int Y_DISPLACEMENT = 50;
    public static final double WIDTH_IMG = Game.gameWindow.frame.getWidth() * SCALE;
    public static final double HEIGHT_IMG = Game.gameWindow.frame.getHeight() * SCALE;

    /**
     * Constructs a new MenuState, loading the menu background image.
     */
    public MenuState() {
        background = Images.getImage(MENU_UI);
    }

    /**
     * Renders the menu state. Currently not used because the main rendering
     * is done in paintComponent().
     *
     * @param g the Graphics object to draw on
     */
    @Override
    public void render(Graphics g) {
        // Rendering is handled in paintComponent(). If you decide to move
        // it here, uncomment the code below or add your own logic.
        /*
        g.drawImage(
            background, X_DISPLACEMENT, Y_DISPLACEMENT,
            (int) WIDTH_IMG, (int) HEIGHT_IMG, null
        );

        g.setColor(Color.RED);
        g.drawRect(
            X_DISPLACEMENT, Y_DISPLACEMENT,
            (int)Game.menuInterface.dimension[0],
            (int)Game.menuInterface.dimension[1]
        );

        g.drawRect(
            X_DISPLACEMENT,
            (int)(2 * Game.menuInterface.dimension[1] + 50),
            (int)Game.menuInterface.dimension[0],
            (int)Game.menuInterface.dimension[1]
        );
        */
    }

    /**
     * Updates any logic necessary for the menu (e.g. animations). Not used
     * in this implementation.
     */
    @Override
    public void update() {
        // No dynamic updates for the menu in this version.
    }

    /**
     * Called by Swing to draw the component. Displays the background image
     * and draws rectangles indicating button boundaries for debugging.
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(
                background, X_DISPLACEMENT, Y_DISPLACEMENT,
                (int) WIDTH_IMG, (int) HEIGHT_IMG, null
        );

        g.setColor(Color.RED);
        g.drawRect(
                X_DISPLACEMENT, Y_DISPLACEMENT,
                (int) Game.menuInterface.dimension[0],
                (int) Game.menuInterface.dimension[1]
        );

        g.drawRect(
                X_DISPLACEMENT,
                (int) (2 * Game.menuInterface.dimension[1] + 50),
                (int) Game.menuInterface.dimension[0],
                (int) Game.menuInterface.dimension[1]
        );
    }
}
