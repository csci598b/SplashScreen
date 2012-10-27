package edu.mines.csci598.splashscreen.highscores;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

//Andrew Suter-Morris
public class PlayerHighScoreInformation implements Serializable {

    private ImageIcon _playerImage;
    private String _playerInitials;
    private double _playerScore;
    private Timestamp _playerTime;

    public PlayerHighScoreInformation(String playerInitials, double playerScore, ImageIcon playerImage, Timestamp playerTime) {
        _playerInitials = playerInitials;
        _playerScore = playerScore;
        _playerImage = playerImage;
        _playerTime = playerTime;
    }

    public ImageIcon getPlayerPlayerImage() {
        return _playerImage;
    }

    public void setPlayerImage(ImageIcon playerPlayerImage) {
        this._playerImage = playerPlayerImage;
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
