package GUI;

import Stories.IcrossingStrategy;
import Stories.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RCGUI extends JPanel implements MouseListener {

    // ==========================================================
    // Fields (hotspots)
    // ==========================================================
    JCheckBox leftCheckBox[] = {new JCheckBox(), 
        new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};
    JCheckBox rightCheckBox[] = {new JCheckBox(), 
        new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};
    JButton[] playButtons = {new JButton("<<"), 
        new JButton("Undo"), new JButton("Redo"), new JButton(">>"), new JButton("Result")};
    JButton[] menuButtons = {new JButton("Story 1"), 
        new JButton("Story 2"), new JButton("Reset"), new JButton("Load"), new JButton("Save")};
    // ==========================================================
    // Private Fields
    // ==========================================================
    private Story1 ics1 = new Story1();
    private Story2 ics2 = new Story2();
    private int TYPE = 0;
    private boolean init = true;
    private static RCGUI theView;
    // ==========================================================
    // Constructor
    // ==========================================================
    public RCGUI() {
        creatMainButtons();
        addMouseListener(this);
        RCGUI.theView = this;
        //engine = GameController.createSingleton(this, null);
    }
    
    // ==========================================================
    // Setters and Getters and Button Listeners
    // ==========================================================
    
    
    void addPlayButton0Listener(ActionListener listenerforPlayButton0){
        playButtons[0].addActionListener(listenerforPlayButton0);
    }

    void addPlayButton1Listener(ActionListener listenerforPlayButton1){
        playButtons[1].addActionListener(listenerforPlayButton1);
    }
    
    void addPlayButton2Listener(ActionListener listenerforPlayButton2){
        playButtons[2].addActionListener(listenerforPlayButton2);
    }
    
    void addPlayButton3Listener(ActionListener listenerforPlayButton3){
        playButtons[3].addActionListener(listenerforPlayButton3);
    }
    
    void addPlayButton4Listener(ActionListener listenerforPlayButton4){
        playButtons[4].addActionListener(listenerforPlayButton4);
    }
    
    void addMenuButton0Listener(ActionListener listenerforMenuButton0){
        menuButtons[0].addActionListener(listenerforMenuButton0);
    }
    
    void addMenuButton1Listener(ActionListener listenerforMenuButton1){
        menuButtons[1].addActionListener(listenerforMenuButton1);
    }
    
    void addMenuButton2Listener(ActionListener listenerforMenuButton2){
        menuButtons[2].addActionListener(listenerforMenuButton2);
    }
    
    void addMenuButton3Listener(ActionListener listenerforMenuButton3){
        menuButtons[3].addActionListener(listenerforMenuButton3);
    }
    
    void addMenuButton4Listener(ActionListener listenerforMenuButton4){
        menuButtons[4].addActionListener(listenerforMenuButton4);
    }

    public Story1 getIcs1() {return ics1;}

    public void setIcs1(Story1 ics1) {this.ics1 = ics1;}

    public Story2 getIcs2() { return ics2;}

    public void setIcs2(Story2 ics2) {this.ics2 = ics2;}

    public int getTYPE() {return TYPE;}

    public void setTYPE(int TYPE) {this.TYPE = TYPE;}

    public boolean isInit() { return init;}

    public void setInit(boolean init) {this.init = init;}
  
    public static RCGUI getTheView() {
        return RCGUI.theView;
    }

    public static void setTheView(RCGUI theView) {
        RCGUI.theView = theView;
    }
    
    // ==========================================================
    // Paint Methods (View)
    // ==========================================================
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (init) {
            return;
        }
        paintObjectsOnLeft(g, TYPE);
        paintObjectsOnRight(g, TYPE);
    }

    public void initialize(int story) {
        creatPlayButtons();
        makeCheckBoxes(story);
        for (int i = 0; i < rightCheckBox.length; i++) {
            this.rightCheckBox[i].setEnabled(false);
        }
        repaint();
    }

    public void creatMainButtons() {
        for (int i = 0; i < this.menuButtons.length; i++) {
            this.menuButtons[i].setBounds(150 + i * 100, 20, 100, 20);
            this.add(this.menuButtons[i]);
            menuButtons[i].setEnabled(true);
        }
    }

    public void creatPlayButtons() {
        for (int i = 0; i < this.playButtons.length; i++) {
            this.playButtons[i].setBounds(150 + i * 100, 620, 100, 20);
            this.add(this.playButtons[i]);
            if (i < 3) {
                this.playButtons[i].setEnabled(false);
            }
        }
    }

    public void makeCheckBoxes(int story) {

        for (int i = 0; i < leftCheckBox.length; i++) {
            leftCheckBox[i].setBounds(50, (100 + i * 100), 50, 50);
            leftCheckBox[i].setBackground(Color.DARK_GRAY);
            this.add(leftCheckBox[i]);
        }

        for (int i = 0; i < rightCheckBox.length; i++) {
            rightCheckBox[i].setBounds(700, (100 + i * 100), 50, 50);
            System.out.println(rightCheckBox[i].getBounds());
            rightCheckBox[i].setBackground(Color.DARK_GRAY);
            this.add(rightCheckBox[i]);
        }
        if (story == 1) {
            leftCheckBox[4].setVisible(false);
            rightCheckBox[4].setVisible(false);
        } else {
            leftCheckBox[4].setVisible(true);
            rightCheckBox[4].setVisible(true);
        }
    }
  
    public void closeEveryThingNow(){
        for(int i = 0; i < playButtons.length; i++){
           this.leftCheckBox[i].setEnabled(false);
           this.rightCheckBox[i].setEnabled(false);
           this.playButtons[i].setEnabled(false);
            
        }
    }

    public void paintObjectsOnLeft(Graphics g, int story) {
        if (story == 1) {
            for (int i = 0; i < ics1.getInitialCrossers().size(); i++) {
                if (ics1.getLeftBankCrosser().contains(ics1.getInitialCrossers().get(i))) {
                    g.drawImage(ics1.getInitialCrossers().get(i).getImages()[0], 100, (100 + i * 100), this);
                }
            }
        } else {
            for (int i = 0; i < ics2.getInitialCrossers().size(); i++) {
                if (ics2.getLeftBankCrosser().contains(ics2.getInitialCrossers().get(i))) {
                    g.drawImage(ics2.getInitialCrossers().get(i).getImages()[0], 100, (100 + i * 100), this);
                }
            }
        }
    }

    public void paintObjectsOnRight(Graphics g, int story) {
        if (story == 1) {
            for (int i = 0; i < ics1.getInitialCrossers().size(); i++) {
                if (ics1.getRightBankCrosser().contains(ics1.getInitialCrossers().get(i))) {
                    g.drawImage(ics1.getInitialCrossers().get(i).getImages()[0], 620, (100 + i * 100), this);
                }
            }
        } else {
            for (int i = 0; i < ics2.getInitialCrossers().size(); i++) {
                if (ics2.getRightBankCrosser().contains(ics2.getInitialCrossers().get(i))) {
                    g.drawImage(ics2.getInitialCrossers().get(i).getImages()[0], 620, (100 + i * 100), this);
                }
            }
        }
    }

    private static void createAndShowGUI() {

        // Create and set up the window
        JFrame frame = new JFrame("RiverCrossing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        RCGUI newContentPane = new RCGUI();
        newContentPane.setOpaque(true);
        newContentPane.setLayout(null);
        frame.setContentPane(newContentPane);
        
        // Display the window
        frame.setSize(800, 700);
        frame.setResizable(false);
        frame.setVisible(true);
        setTheView(newContentPane);
       // return newContentPane;
    }

    public static void view(){
        
         javax.swing.SwingUtilities.invokeLater(RCGUI::createAndShowGUI);
         
    }
    
   /* public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(RCGUI::createAndShowGUI);
    }*/

    // ==========================================================
    // MouseListener Methods 
    // ==========================================================
    @Override
    public void mouseClicked(MouseEvent e) { repaint(); }

    // ----------------------------------------------------------
    // None of these methods will be used
    // ----------------------------------------------------------
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {}

    
    
}
