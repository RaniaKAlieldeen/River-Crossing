
package Commands;
/**
 *
 * @author Rania
 */
public class LoadCommand extends Command{

    @Override
    public void execute() {
        getEngine().loadGame();
    }

    @Override
    public void unexecute() {
        getEngine().saveGame();
    }
    
}
