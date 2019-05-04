package Objects;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Fox extends Carnivore {

    public Fox() {
    }

    @Override
    public BufferedImage[] getImages() {
        BufferedImage b[] = new BufferedImage[2];

        for (int i = 0; i < b.length; i++) {

            try {
                b[i] = ImageIO.read(new File(String.format("fox" + i + ".png")));
            } catch (IOException ex) {
                Logger.getLogger(Fox.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return b;

    }

    @Override
    public ICrosser makeCopy() {
        ICrosser crosser = new Fox();
        return crosser;
    }
}
