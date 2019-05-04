package Objects;


import java.awt.image.BufferedImage;

public abstract class Plant implements ICrosser {

    public Plant() {
    }

    
    
    @Override
    public boolean canSail() {
        return false;
    }

    @Override
    public int getEatingRank() {
        return 3;
    }

    @Override
    public BufferedImage[] getImages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWeight(double weight) {
        
    }

    @Override
    public double getWeight() {
        return 0.0;  }

    @Override
    public abstract ICrosser makeCopy();

    @Override
    public void setLabelToBeShown(String label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabelToBeShown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
