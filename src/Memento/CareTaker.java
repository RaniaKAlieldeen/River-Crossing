
package Memento;

import java.util.ArrayList;

/**
 *
 * @author Rania
 */
public class CareTaker {
    ArrayList<Memento> savedSteps = new ArrayList<>();
    
    public void addMemento(Memento m) { savedSteps.add(m); }

    public Memento getMemento() {
        if(!savedSteps.isEmpty())
            return savedSteps.remove(savedSteps.size()-1); 
        return null;
    }

}
