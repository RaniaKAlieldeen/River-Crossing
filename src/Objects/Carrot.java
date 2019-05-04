package Objects;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Carrot extends Plant {

    public Carrot() {
    }
    
    @Override
    public BufferedImage[] getImages() {
        BufferedImage c[] = new BufferedImage[2];

        for (int i = 0; i < c.length; i++) {

            try {
                c[i] = ImageIO.read(new File(String.format("carrot" + i + ".png")));
            } catch (IOException ex) {
                Logger.getLogger(Carrot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }
    
    @Override
    public ICrosser makeCopy() {
        ICrosser crosser = new Carrot();
        return crosser;
    }
}