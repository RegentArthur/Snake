package GameObject;

import Main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The {@code EntityType} class is an abstract base class representing entities in the game.
 *
 * <p>This class provides common properties and methods for game entities, including position,
 * dimensions, direction, and rendering. Subclasses can extend this class to define specific
 * types of entities such as snake segments or other game objects.
 */
public abstract class EntityType {

    public BufferedImage img;
    public int[] position = new int[2]; // 0: X, 1: Y,  start counting with 1
    public int width, height;
    public int direction; // 0: North, 1: South, 2: East, 3: West
    public BufferedImage north;
    public BufferedImage south;
    public BufferedImage east;
    public BufferedImage west;
    public int turningPointIndex = 0;

    /**
     * Renders the entity on the screen.
     *
     * @param g the {@link Graphics} object used for drawing
     */
    public void render(Graphics g){
        g.drawImage(img, position[0], position[1], GamePanel.TILES_LENGTH, GamePanel.TILES_LENGTH, null);
    }

    /**
     * Default constructor for {@code EntityType}.
     */
    public EntityType(){}

    /**
     * Constructs an {@code EntityType} object with the specified direction and position.
     *
     * @param direction the direction of the entity (0: North, 1: South, 2: East, 3: West)
     * @param xPos the x-coordinate of the entity in grid units
     * @param yPos the y-coordinate of the entity in grid units
     */
    public EntityType(int direction, int xPos, int yPos){
        this.direction = direction;
        this.position[0] = xPos*GamePanel.TILES_LENGTH;
        this.position[1] = yPos*GamePanel.TILES_LENGTH;
    }

    /**
     * Retrieves the graphical representation of the entity based on its direction.
     *
     * @param direction the direction of the entity (0: North, 1: South, 2: East, 3: West)
     * @return the {@link BufferedImage} corresponding to the entity's direction
     * @throws IllegalArgumentException if the direction is invalid
     */
    public BufferedImage getSegment(int direction){
        return switch (direction){
            case 0 -> north;
            case 1 -> south;
            case 2 -> east;
            case 3 -> west;
            default -> throw new IllegalArgumentException("Invalid direction index: " + direction);
        };

    }

    /**
     * Determines if this entity occupies the same grid square as another entity.
     *
     * @param object the other {@code EntityType} object to compare
     * @return {@code true} if both entities are in the same grid square, {@code false} otherwise
     */
    public <T extends EntityType> boolean isInSameSquare(T object){
        double x1 = Math.round((double)this.position[0]/ GamePanel.TILES_LENGTH);
        double y1 = Math.round((double)this.position[1]/ GamePanel.TILES_LENGTH);
        double x2 = Math.round((double)object.position[0]/ GamePanel.TILES_LENGTH);
        double y2 = Math.round((double)object.position[1]/ GamePanel.TILES_LENGTH);
        return x1==x2 && y1==y2;
    }
}
