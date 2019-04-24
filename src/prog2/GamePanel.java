/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    boolean left=true;
    int x = 0;
    Timer t = new Timer(100, this);
    boolean moveright = false;

    public GamePanel() {
        setBackground(Color.BLACK);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon im=null ;
        if(left==true)
        im= new ImageIcon("farmer.png");
        else
        im=new ImageIcon("");
        g.drawImage(im.getImage(), x, 20, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (moveright == true) {
            if (x < 200) {
                x += 10;
            }else
                moveright=false;
        }
        repaint();
    }

    public void moveRight() {
        moveright = true;
    }

    public void moveLeft() {
    }
}
