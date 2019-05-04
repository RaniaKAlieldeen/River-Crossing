package Objects;

import java.awt.image.BufferedImage;

public abstract class Carnivore implements ICrosser {

    private double weight = 0;
    
    public Carnivore(){
    }

    @Override
    public boolean canSail() {
        return false;
    }

    @Override
    public void setWeight(double Weight) {
        this.weight = Weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public int getEatingRank() {
        return 1;
    }

    @Override
    public BufferedImage[] getImages() {
        return null;
    }

    @Override
    public abstract ICrosser makeCopy() ;

    @Override
    public void setLabelToBeShown(String label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabelToBeShown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
