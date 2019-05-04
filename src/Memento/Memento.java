
package Memento;

import Objects.ICrosser;
import java.util.ArrayList;

/**
 *
 * @author Rania
 */
public class Memento {
 
    private ArrayList<ICrosser> boatCrossers = new ArrayList<>();
    private int noOfSails;
    
    
    public Memento(ArrayList<ICrosser> lastSail, int score){
        this.noOfSails = score;
        for(ICrosser c : lastSail)
            this.boatCrossers.add(c);

    }

    public ArrayList<ICrosser> getBoatCrossers() {
        return boatCrossers;
    }

    public void setBoatCrossers(ArrayList<ICrosser> boatCrossers) {
        this.boatCrossers = boatCrossers;
    }

    
    public int getNoOfSails() {
        return noOfSails;
    }

    public void setNoOfSails(int noOfSails) {
        this.noOfSails = noOfSails;
    }
    
    
} 
