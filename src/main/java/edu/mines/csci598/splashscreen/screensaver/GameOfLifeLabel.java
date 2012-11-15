package edu.mines.csci598.splashscreen.screensaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameOfLifeLabel extends JLabel implements MouseListener {
    private boolean mouseDown = false;
    static final int size = 15;
    static final Dimension dim = new Dimension(size, size);
    static final Color[] color = {Color.LIGHT_GRAY, Color.BLUE};
    private int state, newState;
    private int howManyNeighbor;
    private GameOfLifeLabel[] neighbour = new GameOfLifeLabel[8];

    GameOfLifeLabel() {
        state = newState = 0;			// Dead
        setOpaque(true);				// so color will be showed
        setBackground(color[0]);
        addMouseListener(this);			// to select new LIVE cells
        this.setPreferredSize(dim);
    }

    void addNeighbour(GameOfLifeLabel n) {
        neighbour[howManyNeighbor++] = n;
    }
    // to see if I should live or not
    void checkState() {
        // number alive around
        int howManyLive = 0;
        // see the state of my neighbour
        for(int i = 0; i < howManyNeighbor; i++)
            howManyLive += neighbour[i].state;
        // newState
        if(state == 1) {				// if alive
            if(howManyLive < 2)				// 1.Any live cell with fewer than two live neighbours dies
                newState = 0;
            if(howManyLive > 3)				// 2.Any live cell with more than three live neighbours dies
                newState = 0;
        }
        else {
            if(howManyLive == 3)			// 4.Any dead cell with exactly three live neighbours becomes a live cell
                newState = 1;
        }
    }
    // after the run switch the state to new state
    void updateState() {
        if(state != newState) {		// do the test to avoid re-setting same color for nothing
            state = newState;
            setBackground(color[state]);
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }
    // if the mouse enter a cell and it is down we make the cell alive
    public void mouseEntered(MouseEvent arg0) {
        if(mouseDown) {
            state = newState = 1;
            setBackground(color[1]);
        }
    }
    @Override
    public void mouseExited(MouseEvent arg0) {
    }
    // if the mouse is pressed on a cell you register the fact that it is down
    // and make that cell alive
    public void mousePressed(MouseEvent arg0) {
        mouseDown = true;
        state = newState = 1;
        setBackground(color[1]);
    }
    // turn off the fact that the cell is down
    public void mouseReleased(MouseEvent arg0) {
        mouseDown = false;
    }
}
