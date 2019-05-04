package Objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Goat extends Harbivore {

    public Goat() {
    }

    @Override
    public BufferedImage[] getImages() {
        BufferedImage h[] = new BufferedImage[2];
        for (int i = 0; i < h.length; i++) {

            try {
                h[i] = ImageIO.read(new File(String.format("goat" + i + ".png")));
            } catch (IOException ex) {
                Logger.getLogger(Goat.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return h;
    }

    @Override
    public ICrosser makeCopy() {
        ICrosser crosser = new Goat();
        return crosser;
    }
}