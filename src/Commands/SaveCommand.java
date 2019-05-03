
package Commands;

import GUI.GameController;

/**
 *
 * @author Rania
 */
public class SaveCommand implements ICommands{

    private GameController gControl;
    
    @Override
    public void execute() {
        gControl.saveGame();
    }

    @Override
    public void undo() {
        gControl.loadGame();
    }
    
}
