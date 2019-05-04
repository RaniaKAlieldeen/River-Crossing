package Objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Cabbage extends Plant {

    public Cabbage() {
    }
    
    @Override
    public BufferedImage[] getImages() {
        BufferedImage c[] = new BufferedImage[2];

        for (int i = 0; i < c.length; i++) {

            try {
                c[i] = ImageIO.read(new File(String.format("cabbage" + i + ".png")));
            } catch (IOException ex) {
                Logger.getLogger(Cabbage.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return c;
    }

    @Override
    public ICrosser makeCopy() {
        ICrosser crosser = new Cabbage();
        return crosser;
    }
}