package GUI;

import Objects.ICrosser;
import Stories.IcrossingStrategy;
import Stories.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GameController implements IRiverCrossingController {

    IcrossingStrategy str;//strategy 
    private int numberOfSails = 0;
    private boolean isOnLeftBank = true;
    private List<ICrosser> boatCrossers = new ArrayList<>();
    private RCGUI theView;

    private static GameController engine = null;
    
    public static GameController createSingleton(){
        if(engine == null ){
            RCGUI theView = RCGUI.getTheView();
            System.out.println("view is " + theView==null);
            engine = new GameController(theView);
            
        }
        return engine;
    }
    
    
    public GameController(RCGUI theView){
        this.theView = theView;
        this.theView.addPlayButton0Listener(new listenerforPlayButton0());
        this.theView.addPlayButton3Listener(new listenerforPlayButton3());
        this.theView.addMenuButton0Listener(new listenerforMenuButton0());
        this.theView.addMenuButton1Listener(new listenerforMenuButton1());
        this.theView.addMenuButton2Listener(new listenerforMenuButton2());
        this.theView.addMenuButton3Listener(new listenerforMenuButton3());
        this.theView.addMenuButton4Listener(new listenerforMenuButton4());
        
        
    }

    public RCGUI getTheView() {
        return theView;
    }

    @Override
    public void newGame(IcrossingStrategy gameStrategy) {
        str = gameStrategy;
    }

    @Override
    public void resetGame() {
        this.numberOfSails = 0;
        this.isOnLeftBank = false;
        newGame(str);
        getEnabledAndDisabled();
    }

    @Override
    public String[] getInstructions() {
        return this.str.getInstructions();
    }

    @Override
    public List<ICrosser> getCrossersOnRightBank() {
        return (this.str.getType() == 1) ? ((Story1) this.str).getRightBankCrosser() : ((Story2) this.str).getRightBankCrosser();

    }

    @Override
    public List<ICrosser> getCrossersOnLeftBank() {
        return (this.str.getType() == 1) ? ((Story1) this.str).getLeftBankCrosser() : ((Story2) this.str).getLeftBankCrosser();
    }

    @Override
    public boolean isBoatOnTheLeftBank() {
        return this.isOnLeftBank;
    }

    @Override
    public int getNumberOfSails() {
        return this.numberOfSails;
    }

    @Override
    public boolean canMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
        List<ICrosser> tempRight = new ArrayList<>();
        for(int i = 0; i < getCrossersOnRightBank().size(); i++)
            tempRight.add(getCrossersOnRightBank().get(i));

        List<ICrosser> tempLeft = new ArrayList<>();
        for(int i = 0; i < getCrossersOnLeftBank().size(); i++)
            tempLeft.add(getCrossersOnLeftBank().get(i));
        
        if (fromLeftToRightBank) {
            for (int i = 0; i < crossers.size(); i++) {
                tempRight.add(crossers.get(i));
                tempLeft.remove(crossers.get(i));
            }
        } else {
            for (int i = 0; i < crossers.size(); i++) {
                tempRight.remove(crossers.get(i));
                tempLeft.add(crossers.get(i));
            }
        }
        return this.str.isValid(tempRight, tempLeft, crossers);
    }

    @Override
    public void doMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
        this.numberOfSails++;
        if (fromLeftToRightBank) {
            for (int i = 0; i < crossers.size(); i++) {
                if (str.getType() == 1) {
                    ((Story1) str).addRightBankCrosser(crossers.get(i));
                    ((Story1) str).removeLeftBankCrosser(crossers.get(i));

                } else if (str.getType() == 2) {
                    ((Story2) str).addRightBankCrosser(crossers.get(i));
                    ((Story2) str).removeLeftBankCrosser(crossers.get(i));
                }
            }
        } else {
            for (int i = 0; i < crossers.size(); i++) {
                if (str.getType() == 1) {
                    ((Story1) str).removeRightBankCrosser(crossers.get(i));
                    ((Story1) str).addLeftBankCrosser(crossers.get(i));

                } else if (str.getType() == 2) {
                    ((Story2) str).removeRightBankCrosser(crossers.get(i));
                    ((Story2) str).addLeftBankCrosser(crossers.get(i));
                }
            }
        }
        this.isOnLeftBank = (this.isOnLeftBank == false) ? true : false;
    }

    @Override
    public boolean canUndo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canRedo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undo() {
        if (this.canUndo()) {
            
        }
    }

    @Override
    public void redo() {
        if (this.canRedo()) {
            
        }
    }

    @Override
    public void saveGame() {
        //save list of crossers on left bank, right bank and where the boat is
        try{
            FileOutputStream f = new FileOutputStream(new File("./person.xml"));
            XMLEncoder encoder = new XMLEncoder(f);
            List<ICrosser> lbCrossers = getCrossersOnLeftBank();
            List<ICrosser> rbCrossers = getCrossersOnRightBank();
            for(ICrosser c: lbCrossers)
                System.out.println(c.getEatingRank()+c.getWeight());                //encoder.writeObject(c);
            for(ICrosser c: rbCrossers)
                System.out.println(c.getEatingRank()+c.getWeight()); 
                //encoder.writeObject(c);
            encoder.writeObject(isBoatOnTheLeftBank());
            encoder.close();
            f.close();
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    
    }

    @Override
    public void loadGame() {
        //load list of crossers on left bank , right bank and where the boat is
    }

    @Override
    public List<List<ICrosser>> solveGame() {
        return null;
        /**the idea is that, at any point the user will be in is an accepted state 
        *to reach the final solution...
        *so the idea is to have the best solution steps saved,
        *when user asks for solution, the method loops through saved game to find
        *the common state with user's last state, then continue with the saved steps from there
        * Steps:
        * save a state list: for example the solution for story1 is 8 steps 
        * so for CrossersOnRighhtBank: list of length 8, index is step number
        *          CrossersOnLeftBank: list of length 8, index is step number
        *            IsBoatOnLeftSide: list of length 8, index is step number
        *                       Score: score should be handled as well..
        * When user clicks on solve game button, the current state the user stopped at
        * will be compared to the lists above, then the program will go to next index,
        * and solve the game accordingly.
        * 
        */
    }

    public List<ICrosser> getCrossersOnBoat(ArrayList<Integer> x) {
        this.boatCrossers.clear();
        for (int i = 0; i < x.size(); i++) {
            this.boatCrossers.add((str.getInitialCrossers().get(x.get(i))));
        }
        return this.boatCrossers;
    }

    @Override
    public List<ICrosser> getCrossersOnBoat() {
        return this.boatCrossers;
    }

    public void getEnabledAndDisabled() {
        if (isBoatOnTheLeftBank()) {
            theView.playButtons[0].setEnabled(false);//left button disabled
            theView.playButtons[3].setEnabled(true);//right button enabled
            for (int i = 0; i < theView.leftCheckBox.length; i++) {
                theView.leftCheckBox[i].setSelected(false);//clear left selection
                theView.leftCheckBox[i].setEnabled(true);//enable left selection
                theView.rightCheckBox[i].setSelected(false);//clear right selection
                theView.rightCheckBox[i].setEnabled(false);//disable right selection
            }
        } else {//if boat on right side
            theView.playButtons[0].setEnabled(true);//left button enabled
            theView.playButtons[3].setEnabled(false);//rigt butoon disabled
            for (int i = 0; i < theView.leftCheckBox.length; i++) {
                theView.leftCheckBox[i].setSelected(false);//clear left selection
                theView.leftCheckBox[i].setEnabled(false);//disable left selection
                theView.rightCheckBox[i].setSelected(false);//clear right selection
                theView.rightCheckBox[i].setEnabled(true);//enable left selection
            }
        }
    }
    
    class listenerforMenuButton0 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("im inside the action even fn for menu button0 ");
            theView.setInit(false);
            newGame((IcrossingStrategy) theView.getIcs1());
            theView.setTYPE(1);
            theView.initialize(theView.getTYPE());
            theView.repaint();
            getEnabledAndDisabled();
        }
    }

    class listenerforMenuButton1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theView.setInit(false);
            newGame((IcrossingStrategy) theView.getIcs2());
            theView.setTYPE(2);
            theView.initialize(theView.getTYPE());
            theView.repaint();
            getEnabledAndDisabled();
        }
    }
    
    class listenerforMenuButton2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            resetGame();
        } 
    
    }
    
    class listenerforMenuButton3 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            loadGame();
        } 
    
    }
    
    class listenerforMenuButton4 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            saveGame();
        } 
    
    }

    class listenerforPlayButton0 implements ActionListener{
        
        ArrayList<Integer> crossers = new ArrayList<>();
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("ana da5lat gowa");
                for (int i = 0; i < theView.rightCheckBox.length; i++) {
                    if (theView.rightCheckBox[i].isSelected()) {
                        crossers.add(i);
                    }
                }
                System.out.println("RCGUI --> [OnBoat] |B|" + getCrossersOnBoat(crossers));
                boolean valid = isBoatOnTheLeftBank();
                if (canMove(getCrossersOnBoat(crossers), valid)) {
                    doMove(getCrossersOnBoat(crossers), valid);
                } else {
                    JOptionPane.showMessageDialog(null, "Error move! please be smarter :)");
                }
                System.out.println("RCGUI -> leftBankCrosser |B| " + getCrossersOnLeftBank().size());
                System.out.println("RCGUI -> rightBankCrosser |B| " + getCrossersOnRightBank().size());
                theView.repaint();
                crossers.clear();
                getEnabledAndDisabled();
            }   
        
        
        
    }

    class listenerforPlayButton1 implements ActionListener{
        
        ArrayList<Integer> crossers = new ArrayList<>();
        
        @Override
        public void actionPerformed(ActionEvent e) {}}
    
    class listenerforPlayButton2 implements ActionListener{
        
        ArrayList<Integer> crossers = new ArrayList<>();
        
        @Override
        public void actionPerformed(ActionEvent e) {}}
    
    class listenerforPlayButton3 implements ActionListener{
    
            ArrayList<Integer> crossers = new ArrayList<>();
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < theView.leftCheckBox.length; i++) {
                    if (theView.leftCheckBox[i].isSelected()) { crossers.add(i);}
                }
                System.out.println("RCGUI --> [OnBoat] |F|" + getCrossersOnBoat(crossers));
                boolean valid = isBoatOnTheLeftBank();
                System.out.println(valid);
                if (canMove(getCrossersOnBoat(crossers), valid)) {
                    doMove(getCrossersOnBoat(crossers), valid);
                } else {
                    JOptionPane.showMessageDialog(null, "Error move! please be smarter :)");
                }
                System.out.println("RCGUI -> leftBankCrosser |F| " + getCrossersOnLeftBank().size());
                System.out.println("RCGUI -> rightBankCrosser |F| " + getCrossersOnRightBank().size());
                theView.repaint();
                if (getCrossersOnLeftBank().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bravoooooooo!");
                  //  closeEveryThingNow();
                }
                crossers.clear();
                getEnabledAndDisabled();
            }
    }
    
    class listenerforPlayButton4 implements ActionListener{
        
        ArrayList<Integer> crossers = new ArrayList<>();
        
        @Override
        public void actionPerformed(ActionEvent e) {}}
    
    class listenerforPlayButton5 implements ActionListener{
        
        ArrayList<Integer> crossers = new ArrayList<>();
        
        @Override
        public void actionPerformed(ActionEvent e) {}}
    
    
    

    
}
