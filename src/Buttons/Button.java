package Buttons;

import Utilz.Images;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public abstract class Button {

    public double[] location; //0: left most x position, 1: top most y position
    public double[] dimension; //0: width, 1: height
    public BufferedImage img;
    protected double mouseX;
    protected double mouseY;

    public Button(String name){
        img = Images.getImage(name);
        location = new double[2];
        dimension = new double[2];
    }

    public void getMouseLoc(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }


}
