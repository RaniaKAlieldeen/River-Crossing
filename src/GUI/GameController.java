package GUI;

import Commands.Command;
import Memento.CareTaker;
import Memento.Memento;
import Memento.Originator;
import Objects.ICrosser;
import Stories.IcrossingStrategy;
import Stories.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;

public class GameController implements IRiverCrossingController {

    Command command;
    Originator org;
    CareTaker care;
    IcrossingStrategy str;//strategy 
    private int numberOfSails = 0;
    private boolean isOnLeftBank = true;
    private List<ICrosser> boatCrossers = new ArrayList<>();
    private RCGUI theView;
    private Stack undoStack = new Stack();
    private Stack redoStack = new Stack();
    private static GameController engine = null;

    public static GameController getSingleton() {
        if (engine == null) {
            RCGUI theView = RCGUI.getTheView();
            engine = new GameController(theView);
        }
        return engine;
    }

    public GameController(RCGUI theView) {
        this.theView = theView;
        this.theView.addPlayButton0Listener(new listenerforPlayButton0());
        this.theView.addPlayButton1Listener(new listenerforPlayButton1());
        this.theView.addPlayButton2Listener(new listenerforPlayButton2());
        this.theView.addPlayButton3Listener(new listenerforPlayButton3());
        this.theView.addPlayButton4Listener(new listenerforPlayButton4());
        this.theView.addPlayButton5Listener(new listenerforPlayButton5());
        
        this.theView.addMenuButton0Listener(new listenerforMenuButton0());
        this.theView.addMenuButton1Listener(new listenerforMenuButton1());
        this.theView.addMenuButton2Listener(new listenerforMenuButton2());
        this.theView.addMenuButton3Listener(new listenerforMenuButton3());
        this.theView.addMenuButton4Listener(new listenerforMenuButton4());

    }

    public void setNumberOfSails(int numberOfSails) {
        this.numberOfSails = numberOfSails;
    }

    public RCGUI getTheView() {
        return theView;
    }

    public Stack getUndoStack() {
        return undoStack;
    }

    public void setUndoStack(Stack undoStack) {
        this.undoStack = undoStack;
    }

    public Stack getRedoStack() {
        return redoStack;
    }

    public void setRedoStack(Stack redoStack) {
        this.redoStack = redoStack;
    }

    public void setIsOnLeftBank(boolean isOnLeftBank) {
        this.isOnLeftBank = isOnLeftBank;
    }

    @Override
    public void newGame(IcrossingStrategy gameStrategy) {
        str = gameStrategy;
        theView.repaint();
    }

    @Override
    public void resetGame(int x) {
        this.numberOfSails = 0;
        this.isOnLeftBank = true;
        if (x==1) {
            str = new Story1();
            theView.setIcs1((Story1) str);
        } else {
            str = new Story2();
            theView.setIcs2((Story2) str);
        }
        newGame(str);
    }

    @Override
    public String[] getInstructions() {
        return this.str.getInstructions();
    }

    @Override
    public List<ICrosser> getCrossersOnRightBank() {
        return (str.getType() == 1) ? ((Story1) str).getRightBankCrosser() : ((Story2) str).getRightBankCrosser();

    }

    @Override
    public List<ICrosser> getCrossersOnLeftBank() {
        return (str.getType() == 1) ? ((Story1) str).getLeftBankCrosser() : ((Story2) str).getLeftBankCrosser();
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
        for (int i = 0; i < getCrossersOnRightBank().size(); i++) {
            tempRight.add(getCrossersOnRightBank().get(i));
        }

        List<ICrosser> tempLeft = new ArrayList<>();
        for (int i = 0; i < getCrossersOnLeftBank().size(); i++) {
            tempLeft.add(getCrossersOnLeftBank().get(i));
        }

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
        this.isOnLeftBank = (this.isOnLeftBank == false);
    }

    @Override
    public boolean canUndo() { return !undoStack.isEmpty();}

    @Override
    public boolean canRedo() { return !redoStack.isEmpty();}

    @Override
    public void undo() {
        
        List<ICrosser> crossers = new ArrayList<>();
        ArrayList<ICrosser> onBoat = new ArrayList<>();
        for (ICrosser c : boatCrossers) { onBoat.add(c.makeCopy());}
        redoStack.push(onBoat);
        crossers = (List<ICrosser>) undoStack.pop();
        doMove(crossers, isOnLeftBank);
        /*
        List<ICrosser> crossers = new ArrayList<>();
        crossers = org.restoreFromMemento(care.getMemento());
        doMove(crossers, isOnLeftBank);
        */
    }

    @Override
    public void redo() {
        
        List<ICrosser> crossers = new ArrayList<>();
        ArrayList<ICrosser> onBoat = new ArrayList<>();
        for (ICrosser c : boatCrossers) {onBoat.add(c.makeCopy());}
        undoStack.push(onBoat);
        crossers = (List<ICrosser>) redoStack.pop();
        doMove(crossers, isOnLeftBank);
        


    }

    @Override
    public void saveGame() {
        //save list of crossers on left bank, right bank and where the boat is
        try {
            FileOutputStream f = new FileOutputStream(new File("./person.xml"));
            XMLEncoder encoder = new XMLEncoder(f);
            List<ICrosser> lbCrossers = getCrossersOnLeftBank();
            List<ICrosser> rbCrossers = getCrossersOnRightBank();
            for (ICrosser c : lbCrossers) {
                System.out.println(c.getEatingRank() + c.getWeight());//encoder.writeObject(c);
            }
            for (ICrosser c : rbCrossers) {
                System.out.println(c.getEatingRank() + c.getWeight());
            }
            //encoder.writeObject(c);
            encoder.writeObject(isBoatOnTheLeftBank());
            encoder.close();
            f.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadGame() {
        //load list of crossers on left bank , right bank and where the boat is
    }

    @Override
    public List<List<ICrosser>> solveGame() {return null;}

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
            theView.playButtons[1].setEnabled(true);
            theView.playButtons[2].setEnabled(true);
            for (int i = 0; i < theView.leftCheckBox.length; i++) {
                theView.leftCheckBox[i].setSelected(false);//clear left selection
                theView.leftCheckBox[i].setEnabled(true);//enable left selection
                theView.rightCheckBox[i].setSelected(false);//clear right selection
                theView.rightCheckBox[i].setEnabled(false);//disable right selection
            }
        } else {//if boat on right side
            theView.playButtons[0].setEnabled(true);//left button enabled
            theView.playButtons[3].setEnabled(false);//rigt butoon disabled
            theView.playButtons[1].setEnabled(true);
            theView.playButtons[2].setEnabled(true);
            for (int i = 0; i < theView.leftCheckBox.length; i++) {
                theView.leftCheckBox[i].setSelected(false);//clear left selection
                theView.leftCheckBox[i].setEnabled(false);//disable left selection
                theView.rightCheckBox[i].setSelected(false);//clear right selection
                theView.rightCheckBox[i].setEnabled(true);//enable left selection
            }
        }
    }
////============================================================================
    /*button listeners*/
////============================================================================
    //menu 0 done
    class listenerforMenuButton0 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //System.out.println("im inside the action even fn for menu button0 ");
            theView.setInit(false);
            newGame((IcrossingStrategy) theView.getIcs1());
            theView.setTYPE(1);
            theView.initialize(theView.getTYPE());
            theView.repaint();
            undoStack.clear();
            redoStack.clear();
            getEnabledAndDisabled();
            JOptionPane.showMessageDialog(null, str.getInstructions());
        }
    }
    //menu 1 done
    class listenerforMenuButton1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theView.setInit(false);
            newGame((IcrossingStrategy) theView.getIcs2());
            theView.setTYPE(2);
            theView.initialize(theView.getTYPE());
            theView.repaint();
            undoStack.clear();
            redoStack.clear();
            getEnabledAndDisabled();
            JOptionPane.showMessageDialog(null, str.getInstructions());
        }
    }
    //menu 2 done
    class listenerforMenuButton2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(str==null)
                JOptionPane.showMessageDialog(null, "Error: No story has been chosen.");
            else if(str.getType()==1)
                resetGame(1);
            else if(str.getType()==2)
                resetGame(2);
            theView.repaint();
            getEnabledAndDisabled();

        }
    }
    //menu 3 done
    class listenerforMenuButton3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "Still under construction :(");
        }
    }
    //menu 4 done
    class listenerforMenuButton4 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "Still under construction :(");
        }
    }
    //play 0 done
    class listenerforPlayButton0 implements ActionListener {
        ArrayList<Integer> crossers = new ArrayList<>();
        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < theView.rightCheckBox.length; i++) {
                if (theView.rightCheckBox[i].isSelected()) {crossers.add(i);}}
            System.out.println("RCGUI --> [OnBoat] |B|" + getCrossersOnBoat(crossers));
            boolean valid = isBoatOnTheLeftBank();
            if (canMove(getCrossersOnBoat(crossers), valid)) {
                doMove(getCrossersOnBoat(crossers), valid);
                ArrayList<ICrosser> onBoat = new ArrayList<>();
                for(ICrosser c : boatCrossers){onBoat.add(c.makeCopy());}
                undoStack.push(onBoat);
                org.storeInMemento(onBoat, numberOfSails);
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
    //play 1 done
    class listenerforPlayButton1 implements ActionListener {
        ArrayList<Integer> crossers = new ArrayList<>();
        @Override
        public void actionPerformed(ActionEvent e) {
            if (canUndo()) {
                undo();
                theView.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "There Is Nothing To Undo");
            }
            getEnabledAndDisabled();
        }
    }
    //play 2 done
    class listenerforPlayButton2 implements ActionListener {

        ArrayList<Integer> crossers = new ArrayList<>();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (canRedo()) {
                redo();
            } else {
                JOptionPane.showMessageDialog(null, "There Is Nothing To Redo");
            }
            getEnabledAndDisabled();
        }
    }
    //play 3 done
    class listenerforPlayButton3 implements ActionListener {

        ArrayList<Integer> crossers = new ArrayList<>();

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < theView.leftCheckBox.length; i++) {
                if (theView.leftCheckBox[i].isSelected()) {crossers.add(i);}}
            System.out.println("RCGUI --> [OnBoat] |F|" + getCrossersOnBoat(crossers));
            boolean valid = isBoatOnTheLeftBank();
            if (canMove(getCrossersOnBoat(crossers), valid)) {
                doMove(getCrossersOnBoat(crossers), valid);
                ArrayList<ICrosser> onBoat = new ArrayList<>();
                for(ICrosser c : boatCrossers){onBoat.add(c.makeCopy());}
                undoStack.push(onBoat);
                org.storeInMemento(onBoat, numberOfSails);
            } else {
                JOptionPane.showMessageDialog(null, "Error move! please be smarter :)");
            }
            System.out.println("RCGUI -> leftBankCrosser |F| " + getCrossersOnLeftBank().size());
            System.out.println("RCGUI -> rightBankCrosser |F| " + getCrossersOnRightBank().size());
            theView.repaint();
            if (getCrossersOnLeftBank().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Bravoooooooo!\n Score: "+ numberOfSails);
            }
            crossers.clear();
            getEnabledAndDisabled();
        }
    }
    //play 4 done
    class listenerforPlayButton4 implements ActionListener {
        ArrayList<Integer> crossers = new ArrayList<>();
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Number Of Moves    " + getNumberOfSails());
        }
    }
    //play 5 done
    class listenerforPlayButton5 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, str.getInstructions());
        }
    }
}
