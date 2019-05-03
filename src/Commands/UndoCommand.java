
package Commands;

import GUI.GameController;

/**
 *
 * @author Rania
 */
public class UndoCommand implements ICommands{
    private GameController gControl;

    @Override
    public void execute() {
        gControl.undo();
    }

    @Override
    public void undo() {
        gControl.redo();
    }
    
    
    
}
