package edu.mines.csci598.splashscreen.screensaver;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GameofLife  extends JFrame implements ActionListener {

    private GameOfLifeLabel[][] _cells;
    private Timer _timer;
    private static final int NUM_ROWS = 50;
    private static final int NUM_COLS = 50;

    private int generation = 0;
    private JLabel _generationLabel = new JLabel("Generation: 0");
    private JButton _startButton = new JButton("Go");
    private boolean _gameRunning = false;


    GameofLife(int nbRow, int nbCol) {
        super("GameOfLife");

        _cells = new GameOfLifeLabel[nbRow+2][nbCol+2];
        for(int r = 0; r < nbRow+2; r++) {
            for(int c = 0; c < nbCol+2; c++) {
                _cells[r][c] = new GameOfLifeLabel();
            }
        }



        JPanel panel = new JPanel(new GridLayout(nbRow, nbCol, 1, 1));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int r = 1; r < nbRow+1; r++) {
            for(int c = 1; c < nbCol+1; c++) {
                panel.add(_cells[r][c]);
                _cells[r][c].addNeighbour(_cells[r-1][c]); 	// North
                _cells[r][c].addNeighbour(_cells[r+1][c]); 	// South
                _cells[r][c].addNeighbour(_cells[r][c-1]); 	// West
                _cells[r][c].addNeighbour(_cells[r][c+1]); 	// East
                _cells[r][c].addNeighbour(_cells[r-1][c-1]); 	// North West
                _cells[r][c].addNeighbour(_cells[r-1][c+1]); 	// North East
                _cells[r][c].addNeighbour(_cells[r+1][c-1]); 	// South West
                _cells[r][c].addNeighbour(_cells[r+1][c+1]); 	// South East
            }
        }

        int gridSize = NUM_COLS * NUM_ROWS;
        int numRandomElements = gridSize / 5;
        Random rand = new Random();
        for (int i = 0; i < numRandomElements; i++) {
            int randomNum = rand.nextInt(gridSize + 1);
            int xCoord = randomNum / 50;
            int yCoord = randomNum % 50;
            _cells[xCoord][yCoord].setState();
        }

        add(panel, BorderLayout.CENTER);
        panel = new JPanel(new GridLayout(1,3));
        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        _startButton.addActionListener(this);
        buttonPanel.add(_startButton);
        panel.add(buttonPanel);
        _generationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(_generationLabel);

        add(panel, BorderLayout.SOUTH);
        setLocation(20, 20);
        pack();
        setVisible(true);
        _timer = new Timer(100, this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public synchronized void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o == _startButton) {
            _startButton.setEnabled(false);
            _gameRunning = true;
            _timer.setDelay(100);
            _timer.start();
            return;
        }

        _timer.setDelay(100);
        _timer.start();

        if(!_gameRunning)
            return;
        ++generation;
        _generationLabel.setText("Generation: " + generation);
        for (GameOfLifeLabel[] aLabel1 : _cells) {
            for (GameOfLifeLabel anALabel1 : aLabel1) {
                anALabel1.checkState();
            }
        }
        for (GameOfLifeLabel[] aLabel : _cells) {
            for (GameOfLifeLabel anALabel : aLabel) {
                anALabel.updateState();
            }
        }
    }

    public static void main(String[] arg) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameofLife(NUM_COLS, NUM_ROWS);
            }
        });
    }
}