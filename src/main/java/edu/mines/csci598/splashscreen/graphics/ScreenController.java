package edu.mines.csci598.splashscreen.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScreenController extends JPanel {

    List<ScreensaverSection> sections;

    public static void main(String[] args) {
        ScreenController screen = new ScreenController();

        screen.sections = new ArrayList<ScreensaverSection>();
        ScreensaverSection highScoreSection = new HighScoreScreen();
        highScoreSection.initialize(new Point(0, 0), new Point(1280, 720), screen.callback);
        screen.sections.add(highScoreSection);

        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(1280, 720);
        mainFrame.add(screen);
        mainFrame.setVisible(true);
    }

    UpdateScreenCallback callback = new UpdateScreenCallback() {
        @Override
        public void updateScreen() {
            ScreenController.this.updateUI();
        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (ScreensaverSection section : sections) {
            section.draw(g);
        }
    }
}
