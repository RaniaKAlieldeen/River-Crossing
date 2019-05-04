
package Commands;

import GUI.GameController;

/**
 *
 * @author Rania
 */
public class RedoCommand implements ICommands{

    private GameController gControl;
    
    @Override
    public void execute() {
        gControl.redo();
    }

    @Override
    public void unexecute() {
        gControl.undo();
        
    }
    
}
