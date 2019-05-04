
package Memento;

import GUI.GameController;
import Objects.ICrosser;
import java.util.ArrayList;

/**
 *
 * @author Rania
 */
public class Originator {
    
    public Memento storeInMemento(ArrayList<ICrosser> lastSail,int score){
        return new Memento(lastSail,score);
    }
    
    public ArrayList<ICrosser> restoreFromMemento(Memento memento){
        GameController.getSingleton().setNumberOfSails(memento.getNoOfSails()-1);
        return memento.getBoatCrossers();
    }
}
