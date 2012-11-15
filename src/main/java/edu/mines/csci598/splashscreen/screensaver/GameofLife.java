package edu.mines.csci598.splashscreen.screensaver;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameofLife  extends JFrame implements ActionListener {


    private static final int numRows = 50;
    private static final int numCols = 50;

    // the cells labels
    private GameOfLifeLabel[][] cells;
    // timer that fires the next feneration
    private Timer timer;
    // generation counter
    private int generation = 0;
    private JLabel generationLabel = new JLabel("Generation: 0");
    // the 3 buttons
    private JButton
            bGo = new JButton("Go");
    // state of the game (running or pause)
    private boolean gameRunning = false;


    GameofLife(int nbRow, int nbCol) {
        super("GameOfLife");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create the labels (2 more on each size) these wont be shown
        // but will be used in calculating the cells alive around
        cells = new GameOfLifeLabel[nbRow+2][nbCol+2];
        for(int r = 0; r < nbRow+2; r++) {
            for(int c = 0; c < nbCol+2; c++) {
                cells[r][c] = new GameOfLifeLabel();
            }
        }

        // panel in the center with the labels
        JPanel panel = new JPanel(new GridLayout(nbRow, nbCol, 1, 1));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // add each cells (not the one on the border) to the panel and add to each of them its neighbours
        for(int r = 1; r < nbRow+1; r++) {
            for(int c = 1; c < nbCol+1; c++) {
                panel.add(cells[r][c]);
                cells[r][c].addNeighbour(cells[r-1][c]); 	// North
                cells[r][c].addNeighbour(cells[r+1][c]); 	// South
                cells[r][c].addNeighbour(cells[r][c-1]); 	// West
                cells[r][c].addNeighbour(cells[r][c+1]); 	// East
                cells[r][c].addNeighbour(cells[r-1][c-1]); 	// North West
                cells[r][c].addNeighbour(cells[r-1][c+1]); 	// North East
                cells[r][c].addNeighbour(cells[r+1][c-1]); 	// South West
                cells[r][c].addNeighbour(cells[r+1][c+1]); 	// South East
            }
        }

        // now the panel can be added
        add(panel, BorderLayout.CENTER);

        // the bottom panel with the buttons the generation cells and the slider
        // this panel is formed grid panels
        panel = new JPanel(new GridLayout(1,3));
        // another panel for the 3 buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        bGo.addActionListener(this);
        buttonPanel.add(bGo);
        panel.add(buttonPanel);
        generationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(generationLabel);

        // in the JFrame
        add(panel, BorderLayout.SOUTH);
        // put the frame on
        setLocation(20, 20);
        pack();
        setVisible(true);
        // start the thread that run the cycles of life
        timer = new Timer(100, this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // called by the Timer and the JButtons
    public synchronized void actionPerformed(ActionEvent e) {
        // test the JButtons first
        Object o = e.getSource();
        // the go button
        if(o == bGo) {
            bGo.setEnabled(false);					// disable myself
            gameRunning = true;						// flag game is running
            timer.setDelay(100);
            timer.start();
            return;
        }
        // not a JButton so it is the timer
        // set the delay for the next time
        timer.setDelay(100);
        timer.start();
        // if the game is not running wait for next time
        if(!gameRunning)
            return;
        ++generation;
        generationLabel.setText("Generation: " + generation);
        for (GameOfLifeLabel[] aLabel1 : cells) {
            for (GameOfLifeLabel anALabel1 : aLabel1) {
                anALabel1.checkState();
            }
        }
        for (GameOfLifeLabel[] aLabel : cells) {
            for (GameOfLifeLabel anALabel : aLabel) {
                anALabel.updateState();
            }
        }
    }

    // to start the whole thing as a Java application
    public static void main(String[] arg) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameofLife(numCols, numRows);
            }
        });
    }
}