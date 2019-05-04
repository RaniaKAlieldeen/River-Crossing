
package Commands;

import GUI.GameController;

/**
 *
 * @author Rania
 */
public abstract class Command {
    
    public GameController getEngine() {
        return GameController.getSingleton();
    }

    public abstract void execute();

    public abstract void unexecute();
    
}
