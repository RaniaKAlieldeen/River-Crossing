package GUI;


import Objects.ICrosser;
import Stories.IcrossingStrategy;
import java.util.List;

public interface IRiverCrossingController {

    
    public void newGame(IcrossingStrategy gameStrategy);

    /**
     * resets the game without changing the strategy
     */
    public void resetGame();

    /**
     * @return the current strategy instructions if the user want to see them
     */
    public String[] getInstructions();

    /**
     * @return list of crossers on the right bank of the river
     */
    public List<ICrosser> getCrossersOnRightBank();

    /**
     * @return list of crossers on the left bank of the river
     */
    public List<ICrosser> getCrossersOnLeftBank();
    
    /**
     * @return list of crossers on the left bank of the river
     */
    public List<ICrosser> getCrossersOnBoat();

    public boolean isBoatOnTheLeftBank();

    /**
     * @return returns the number of sails that the user have done so far
     */
    public int getNumberOfSails();

    /**
     * @param crossers which the user selected to move
     * @param fromLeftToRightBank boolean to inform the controller with the
     * direction of the current game
     * @return boolean if it is a valid move or not
     */
    public boolean canMove(List<ICrosser> crossers, boolean fromLeftToRightBank);

    /**
     * this method ysed to perform the move if it is valid
     *
     * @param crossers
     * @param fromLeftToRightBank
     */
    public void doMove(List<ICrosser> crossers, boolean fromLeftToRightBank);

    /**
     * @return boolean providing that the undo action can be done or not
     */
    public boolean canUndo();

    /**
     * @return boolean providing that the redo action can be done or not
     */
    public boolean canRedo();

    /**
     * undo to the last game state
     */
    public void undo();

    /**
     * redo the undo actions
     */
    public void redo();

    /**
     * saves the game state (left bank crossers, right bank crossers, number of
     * done sails, position of the boat)
     */
    public void saveGame();

    /**
     * load the saved game state
     */
    public void loadGame();

    /**
     * this function is bonus it returns the boat riders starting from the
     * beginning of the game until the final solution to show the user the
     * solution
     * @return 
     */
    public List<List<ICrosser>> solveGame();
}
