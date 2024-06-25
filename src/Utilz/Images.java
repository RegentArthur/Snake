package Utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Images {
    public static BufferedImage getImage(String filename){
        InputStream is = Images.class.getResourceAsStream("/"+filename);

        BufferedImage img = null;

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
