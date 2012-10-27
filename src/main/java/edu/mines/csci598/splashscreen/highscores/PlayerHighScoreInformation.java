package edu.mines.csci598.splashscreen.highscores;

import java.awt.*;
import java.io.Serializable;
import java.sql.Timestamp;

//Andrew Suter-Morris
public class PlayerHighScoreInformation implements Serializable {

    private Image _playerImage;
    private String _playerInitials;
    private double _playerScore;
    private Timestamp _playerTime;

    public Image getPlayerImage() {
        return _playerImage;
    }

    public void setPlayerImage(Image playerImage) {
        this._playerImage = playerImage;
    }

    public String getPlayerInitials() {
        return _playerInitials;
    }

    public void setPlayerInitials(String playerInitials) {
        this._playerInitials = playerInitials;
    }

    public double getPlayerScore() {
        return _playerScore;
    }

    public void setPlayerScore(double playerScore) {
        this._playerScore = playerScore;
    }

    public Timestamp getPlayerTime() {
        return _playerTime;
    }

    public void set_playerTime(Timestamp playerTime) {
        this._playerTime = playerTime;
    }
}
