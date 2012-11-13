package edu.mines.csci598.splashscreen.screensaver;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GameofLife  extends JFrame implements ActionListener {
    static final Color[] color = {Color.LIGHT_GRAY, Color.BLUE};
    // size in pixel of every cells
    static final int size = 15;
    static final Dimension dim = new Dimension(size, size);
    static final int numRows = 50;
    static final int numCols = 50;

    // the cells labels
    private LifeLabel[][] cells;
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
    // if the mouse is down or not
    private boolean mouseDown = false;

    GameofLife(int nbRow, int nbCol) {
        super("GameOfLife");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create the labels (2 more on each size) these wont be shown
        // but will be used in calculating the cells alive around
        cells = new LifeLabel[nbRow+2][nbCol+2];
        for(int r = 0; r < nbRow+2; r++) {
            for(int c = 0; c < nbCol+2; c++) {
                cells[r][c] = new LifeLabel();
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
        for (LifeLabel[] aLabel1 : cells) {
            for (LifeLabel anALabel1 : aLabel1) {
                anALabel1.checkState();
            }
        }
        for (LifeLabel[] aLabel : cells) {
            for (LifeLabel anALabel : aLabel) {
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

    // A class that extends JLabel but also check for the neigbour
    // when asked to do so
    class LifeLabel extends JLabel implements MouseListener {
        private int state, newState;
        private int howManyNeighbor;
        private LifeLabel[] neighbour = new LifeLabel[8];

        LifeLabel() {
            state = newState = 0;			// Dead
            setOpaque(true);				// so color will be showed
            setBackground(color[0]);
            addMouseListener(this);			// to select new LIVE cells
            this.setPreferredSize(dim);
        }
        // to add a neibour
        void addNeighbour(LifeLabel n) {
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

        // called when the game is reset/clear
        void clear() {
            if(state == 1 || newState == 1) {
                state = newState = 0;
                setBackground(color[state]);
            }
        }

        public void randomizeBoard() {
            //randomly place 500 cells
            int gridSize = numCols * numRows;
            int numRandomElements = gridSize / 5;
            Random rand = new Random();
            for (int i = 0; i < numRandomElements; i++) {
                int randomNum = rand.nextInt(gridSize + 1);
                int xCoord = randomNum / 50;
                int yCoord = randomNum % 50;
                state = newState = 1;
                setBackground(color[1]);
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
}