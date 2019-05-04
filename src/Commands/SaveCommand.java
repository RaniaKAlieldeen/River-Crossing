
package Commands;

/**
 *
 * @author Rania
 */
public class SaveCommand extends Command{

    
    @Override
    public void execute() {
        getEngine().saveGame();
    }

    @Override
    public void unexecute() {
        getEngine().loadGame();
    }
    
}
