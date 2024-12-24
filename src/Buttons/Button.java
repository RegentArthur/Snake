package Buttons;

import Utilz.Images;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * The {@code Button} class serves as an abstract base class for buttons in the game interface.
 *
 * <p>This class provides common functionality for handling button properties such as location,
 * dimensions, and mouse interaction. Subclasses can extend this class to define specific button behaviors.
 */
public abstract class Button {

    public double[] location; //0: left most x position, 1: top most y position
    public double[] dimension; //0: width, 1: height
    public BufferedImage img;
    protected double mouseX;
    protected double mouseY;

    /**
     * Constructs a {@code Button} object and initializes its image, location, and dimensions.
     *
     * @param name the name of the image resource used for the button
     */
    public Button(String name){
        img = Images.getImage(name);
        location = new double[2];
        dimension = new double[2];
    }

    /**
     * Updates the mouse location based on a {@link MouseEvent}.
     *
     * @param e the {@link MouseEvent} representing the mouse event
     */
    public void getMouseLoc(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
