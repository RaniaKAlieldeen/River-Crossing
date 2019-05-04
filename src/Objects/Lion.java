package Objects;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class Lion extends Carnivore {

    public Lion() {
    }

    @Override
    public BufferedImage[] getImages() {
        BufferedImage b[] = new BufferedImage[2];

        for (int i = 0; i < b.length; i++) {

            try {
                b[i] = ImageIO.read(new File(String.format("lion" + i + ".png")));

            } catch (IOException ex) {
                Logger.getLogger(Lion.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return b;
    }

    @Override
    public ICrosser makeCopy() {
        ICrosser crosser = new Lion();
        return crosser;
    }
}
