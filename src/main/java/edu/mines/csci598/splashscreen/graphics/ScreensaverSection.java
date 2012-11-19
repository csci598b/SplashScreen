package edu.mines.csci598.splashscreen.graphics;

import java.awt.*;

public interface ScreensaverSection {
    public void initialize(Point topLeft, Point bottomRight, UpdateScreenCallback callback);
    public void draw(Graphics g);
    public void stop();
}