package GUI;

import Controllers.IcrossingStrategy;
import Stories.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RCGUI extends JPanel implements MouseListener {

    // ==========================================================
    // Fields (hotspots)
    // ==========================================================
    JCheckBox leftCheckBox[] = {new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};
    JCheckBox rightCheckBox[] = {new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};
    JButton[] playButtons = {new JButton("<<"), new JButton("Undo"), new JButton("Redo"), new JButton(">>"), new JButton("Result")};
    JButton[] menuButtons = {new JButton("Story 1"), new JButton("Story 2"), new JButton("Reset"), new JButton("Load"), new JButton("Save")};
    JLabel[] labels ={ new JLabel(" 90KG"),new JLabel(" 80KG"),new JLabel("60KG"),new JLabel("40KG"),new JLabel("20KG")};
    JLabel[] labels1 ={new JLabel("eating rank=1"),new JLabel(" eating rank=2"),new JLabel("eating rank = 3"),new JLabel("TooBigTobeTrue")};
    // ==========================================================
    // Private Fields
    // ==========================================================
    private GameController engine; // Model
    private Story1 ics1 = new Story1();
    private Story2 ics2 = new Story2();
    private int TYPE = 0;
    private boolean init = true;

    // ==========================================================
    // Constructor
    // ==========================================================
    public RCGUI() {

        engine = new GameController();
        addMouseListener(this);
        creatMainButtons();
        playButtons[0].addActionListener(new ActionListener() {
            ArrayList<Integer> crossers = new ArrayList<Integer>();

            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < rightCheckBox.length; i++) {
                    if (rightCheckBox[i].isSelected()) {
                        crossers.add(i);
                    }
                }
                System.out.println("RCGUI --> [OnBoat] |B|" + engine.getCrossersOnBoat(crossers));
                boolean valid = engine.isBoatOnTheLeftBank();
                if (engine.canMove(engine.getCrossersOnBoat(crossers), valid)) {
                    engine.doMove(engine.getCrossersOnBoat(crossers), valid);
                } else {
                    JOptionPane.showMessageDialog(null, "Error move! please be smarter :)");
                }
                System.out.println("RCGUI -> leftBankCrosser |B| " + engine.getCrossersOnLeftBank().size());
                System.out.println("RCGUI -> rightBankCrosser |B| " + engine.getCrossersOnRightBank().size());
                repaint();
                crossers.clear();
                getEnabledAndDisabled();
            }

        });
         playButtons[1].addActionListener(new ActionListener() {
             
              @Override
            public void actionPerformed(ActionEvent e) {
                
                engine.undo();
                 getEnabledAndDisabled();
            }
         });
         playButtons[2].addActionListener(new ActionListener() {
              @Override
            public void actionPerformed(ActionEvent e) {
               
                engine.redo();
                getEnabledAndDisabled();
            }
         });
        
        
        playButtons[3].addActionListener(new ActionListener() {
            ArrayList<Integer> crossers = new ArrayList<Integer>();

            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < leftCheckBox.length; i++) {
                    if (leftCheckBox[i].isSelected()) {
                        crossers.add(i);
                    }
                }
                System.out.println("RCGUI --> [OnBoat] |F|" + engine.getCrossersOnBoat(crossers));
                boolean valid = engine.isBoatOnTheLeftBank();
                System.out.println(valid);
                if (engine.canMove(engine.getCrossersOnBoat(crossers), valid)) {
                    engine.doMove(engine.getCrossersOnBoat(crossers), valid);
                } else {
                    JOptionPane.showMessageDialog(null, "Error move! please be smarter :)");
                }
                System.out.println("RCGUI -> leftBankCrosser |F| " + engine.getCrossersOnLeftBank().size());
                System.out.println("RCGUI -> rightBankCrosser |F| " + engine.getCrossersOnRightBank().size());
                repaint();
                if (engine.getCrossersOnLeftBank().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bravo!");
                  //  closeEveryThingNow();
                }
                crossers.clear();
                getEnabledAndDisabled();
            }

        });
        
        playButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(null, "Number Of Moves    " + engine.getNumberOfSails());
            }
        
        
        });
        
        menuButtons[0].addActionListener((ActionEvent e) -> {
            init = false;
            engine.newGame((IcrossingStrategy) ics1);
            TYPE = 1;
            initialize(TYPE);
            repaint();
        });
        menuButtons[1].addActionListener((ActionEvent e) -> {
            init = false;
            engine.newGame((IcrossingStrategy) ics2);
            TYPE = 2;
            initialize(TYPE);
            repaint();
        });
        menuButtons[2].addActionListener((ActionEvent e) -> { 
            
          ArrayList<Integer> crossers = new ArrayList<Integer>();
           engine.resetGame();
           repaint();
        });
        
        
        
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
        makelabels(story);
       
        for (int i = 0; i < rightCheckBox.length; i++) {
            this.rightCheckBox[i].setEnabled(false);
        }
    }
    
       
        
        
        
        
        
        
        
        
        
    

        
        public void makelabels(int story) {
          if(story==1){
        for (int i = 0; i < labels1.length; i++) {
            labels1[i].setBounds(100, (100 + i * 100), 100, 100);
      
            labels1[i].setBackground(Color.RED);
            this.add(labels1[i]);
            
            
            
        }
         }
          else {
          
          
           for (int i = 0; i < labels.length; i++) {
            labels[i].setBounds(140, (100 + i * 100), 140, 140);
      
            labels[i].setBackground(Color.YELLOW);
            this.add(labels[i]);
            
            
            
        }
          
          
          }
        
        
        }
        
        
        
       
        
        
        
        
        
        
    public void creatMainButtons() {
        for (int i = 0; i < this.menuButtons.length; i++) {
            this.menuButtons[i].setBounds(150 + i * 100, 20, 100, 20);
            this.add(this.menuButtons[i]);
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
            
            labels1[2].setVisible(true);
        } else {
            leftCheckBox[4].setVisible(true);
            rightCheckBox[4].setVisible(true);
        }
    }
    
    
    
    

    public void getEnabledAndDisabled() {
        if (this.engine.isBoatOnTheLeftBank()) {
            this.playButtons[0].setEnabled(false);
            this.playButtons[3].setEnabled(true);
            this.playButtons[1].setEnabled(true);
            this.playButtons[2].setEnabled(true);
            for (int i = 0; i < leftCheckBox.length; i++) {
                leftCheckBox[i].setSelected(false);
                leftCheckBox[i].setEnabled(true);
                rightCheckBox[i].setSelected(false);
                rightCheckBox[i].setEnabled(false);
            }
        } else {
            this.playButtons[0].setEnabled(true);
            this.playButtons[3].setEnabled(false);
             this.playButtons[1].setEnabled(true);
             this.playButtons[2].setEnabled(true);
            for (int i = 0; i < leftCheckBox.length; i++) {
                leftCheckBox[i].setSelected(false);
                leftCheckBox[i].setEnabled(false);
                rightCheckBox[i].setSelected(false);
                rightCheckBox[i].setEnabled(true);
            }
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
        // menuBar = getMenuBar();
        // frame.setMenuBar(menuBar);

        // Display the window
        frame.setSize(800, 700);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        
        javax.swing.SwingUtilities.invokeLater(RCGUI::createAndShowGUI);
    }

    // ==========================================================
    // MouseListener Methods (Controller)
    // ==========================================================
    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
    }

    // ----------------------------------------------------------
    // None of these methods will be used
    // ----------------------------------------------------------
    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
