package edu.mines.csci598.splashscreen.screensaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameOfLifeLabel extends JLabel implements MouseListener {
    private boolean mouseDown = false;
    static final int size = 15;
    static final Dimension dim = new Dimension(size, size);
    static final Color[] color = {Color.DARK_GRAY, Color.LIGHT_GRAY};
    private int state, newState;
    private int howManyNeighbor;
    private GameOfLifeLabel[] neighbour = new GameOfLifeLabel[8];

    GameOfLifeLabel() {
        state = newState = 0;
        setOpaque(true);
        setBackground(color[0]);
        addMouseListener(this);
        this.setPreferredSize(dim);
    }

    void addNeighbour(GameOfLifeLabel n) {
        neighbour[howManyNeighbor++] = n;
    }

    void checkState() {
        int howManyLive = 0;
        for(int i = 0; i < howManyNeighbor; i++)
            howManyLive += neighbour[i].state;
        // newState
        if(state == 1) {
            if(howManyLive < 2)
                newState = 0;
            if(howManyLive > 3)
                newState = 0;
        }
        else {
            if(howManyLive == 3)
                newState = 1;
        }
    }

    void updateState() {
        if(state != newState) {
            state = newState;
            setBackground(color[state]);
        }
    }

    void setState() {
        state = 1;
        setBackground(color[state]);
    }

    public void mouseEntered(MouseEvent arg0) {
        if(mouseDown) {
            state = newState = 1;
            setBackground(color[1]);
        }
    }

    public void mousePressed(MouseEvent arg0) {
        mouseDown = true;
        state = newState = 1;
        setBackground(color[1]);
    }
    // turn off the fact that the cell is down
    public void mouseReleased(MouseEvent arg0) {
        mouseDown = false;
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }
    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

}
