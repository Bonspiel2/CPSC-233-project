package ca.ucalgary.main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class GooeyLogic implements ActionListener {
    
    Timer gameClock;
    GooeyInterface gui;
    
    
    // constructor
    public GooeyLogic(GooeyInterface gui) {
        
        gameClock = new Timer(10, this);
        gameClock.setActionCommand("TIMER");
        gameClock.start();
        this.gui = gui;
        
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("TIMER")) {
            gui.repaint();
        }
        // else if (e.getActionCommand().equals("KEYBOARD"))

        
    }

}
