package Utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Image class provides utility methods for reading image files
 * packaged within the application's resources.
 */
public class Images {
    /**
     * Reads an image file from the application's resources and returns it as a Buffered Image.
     *
     * <p>The method attempts to locate the specified image file within the classpath,
     * reads it using {@link ImageIO}, and converts it into a {@link BufferedImage}.
     *
     * @param filename the name of the image file to be loaded, relative to the root of the classpath
     * @return the loaded image as a {@link BufferedImage}
     * @throws RuntimeException if the image cannot be read or the input stream cannot be closed
     */
    public static BufferedImage getImage(String filename){
        InputStream is = Images.class.getResourceAsStream("/"+filename);

        BufferedImage img;

        try{
           img = ImageIO.read(is);
        }catch(IOException e){
            throw new RuntimeException(e);
        }finally{
            try{
                is.close();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }

        return img;
    }
}
