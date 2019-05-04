package Objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Farmer implements ICrosser {

    private double weight;

    Farmer() {}

    @Override
    public boolean canSail() {
        return true;
    }

    @Override
    public int getEatingRank() {
        return 10;
    }

    @Override
    public BufferedImage[] getImages() {

        BufferedImage f[] = new BufferedImage[2];

        for (int i = 0; i < f.length; i++) {

            try {
                f[i] = ImageIO.read(new File(String.format("farmer" + i + ".png")));
            } catch (IOException ex) {
                Logger.getLogger(Farmer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return f;

    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    

    @Override
    public void setLabelToBeShown(String label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabelToBeShown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ICrosser makeCopy() {
        Farmer crosser = new Farmer();
        crosser.setWeight(this.getWeight());
        return (ICrosser) crosser;    
    }
}
