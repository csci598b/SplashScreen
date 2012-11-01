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
import java.util.Comparator;

//Andrew Suter-Morris
public class PlayerHighScoreInformation implements Serializable {

    private ImageIcon _playerImage;
    private String _playerInitials;
    private double _playerScore;
    private long _playerTime;

    public PlayerHighScoreInformation(String playerInitials, double playerScore, ImageIcon playerImage, long playerTime) {
        _playerInitials = playerInitials;
        _playerScore = playerScore;
        _playerImage = playerImage;
        _playerTime = playerTime;
    }

    public ImageIcon getPlayerImage() {
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

    public long getPlayerTime() {
        return _playerTime;
    }

    public void setPlayerTime(long playerTime) {
        this._playerTime = playerTime;
    }


    public static Comparator<PlayerHighScoreInformation> playerInitialsComporator
            = new Comparator<PlayerHighScoreInformation>() {

        public int compare(PlayerHighScoreInformation playerInformation1, PlayerHighScoreInformation playerInformation2) {

            String initials1 = playerInformation1.getPlayerInitials();
            String initials2 = playerInformation2.getPlayerInitials();
            return initials1.compareTo(initials2);
        }

    };
}
