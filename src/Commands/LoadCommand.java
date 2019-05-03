
package Commands;

import GUI.GameController;

/**
 *
 * @author Rania
 */
public class LoadCommand implements ICommands{

    private GameController gControl;
    
    @Override
    public void execute() {
        gControl.loadGame();
    }

    @Override
    public void undo() {
        gControl.saveGame();
    }
    
}
