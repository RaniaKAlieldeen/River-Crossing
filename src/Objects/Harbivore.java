package Objects;

import Controllers.ICrosser;
import java.awt.image.BufferedImage;

public class Harbivore implements ICrosser{

    public Harbivore(){
    }
    
    @Override
    public boolean canSail() {
        return false;
    }

    @Override
    public int getEatingRank() {
       return 2;
    }

    @Override
    public BufferedImage[] getImages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWeight(double weight) {}

    @Override
    public double getWeight() {
        return 0.0;
    }

    @Override
    public ICrosser makeCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLabelToBeShown(String label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabelToBeShown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
