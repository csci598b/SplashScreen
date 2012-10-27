package edu.mines.csci598.splashscreen.highscores;


import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

//Andrew Suter-Morris
public class PlayerHighScoreSerializationTest {

    private ArrayList<PlayerHighScoreInformation> _scores;
    private ImageIcon _playerImage;
    private Timestamp _playerTime;
    private double _playerScore;
    private String _playerInitials;

    @Before
    public void setup() throws IOException {
        _playerImage = new ImageIcon(getClass().getResource("recycle.jpg"));
        _playerTime = new Timestamp(new Date().getTime());
        _playerScore = 50;
        _playerInitials = "ATM";
    }

    @Test
    public void serializeEmptyPlayerHighScoreList() {
        _scores = new ArrayList<PlayerHighScoreInformation>();
        SerializePlayerInformation.storePlayerHighScoreInformation(_scores, "testOnePlayer.dat");
    }

    @Test
    public void serializeOnePlayerInHighScoreList() {
        _scores = new ArrayList<PlayerHighScoreInformation>();
        _scores.add(new PlayerHighScoreInformation(_playerInitials, _playerScore, _playerImage, _playerTime));
        SerializePlayerInformation.storePlayerHighScoreInformation(_scores, "testOnePlayer.dat");
    }

    @Test
    public void serializeMultiplePlayersInHighScoreList() {
        _scores = new ArrayList<PlayerHighScoreInformation>();
        _scores.add(new PlayerHighScoreInformation(_playerInitials, _playerScore, _playerImage, _playerTime));
        _scores.add(new PlayerHighScoreInformation(_playerInitials, _playerScore+1, _playerImage, _playerTime));
        SerializePlayerInformation.storePlayerHighScoreInformation(_scores, "testMultiplePlayer.dat");
    }
}
