
package Commands;


/**
 *
 * @author Rania
 */
public class UndoCommand extends Command{
 

    
    
    @Override
    public void execute() {
        getEngine().undo();
    }

    @Override
    public void unexecute() {
        getEngine().redo();
    }
    
    
    
}
