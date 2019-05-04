
package Commands;


/**
 *
 * @author Rania
 */
public class RedoCommand extends Command{

    
    @Override
    public void execute() {
        getEngine().redo();
    }

    @Override
    public void unexecute() {
        getEngine().undo();
        
    }
    
}
