package Objects;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Donkey extends Harbivore {

    public Donkey() {
    }

    @Override
    public BufferedImage[] getImages() {
        BufferedImage h[] = new BufferedImage[2];

        for (int i = 0; i < h.length; i++) {

            try {
                h[i] = ImageIO.read(new File(String.format("donkey" + i + ".png")));
            } catch (IOException ex) {
                Logger.getLogger(Donkey.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return h;
    }

}
