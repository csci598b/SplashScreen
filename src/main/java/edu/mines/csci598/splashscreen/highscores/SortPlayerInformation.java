package edu.mines.csci598.splashscreen.highscores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortPlayerInformation {

    public ArrayList<PlayerHighScoreInformation> sortByScore(ArrayList<PlayerHighScoreInformation> playerHighScoreInformationList) {
        Collections.sort(playerHighScoreInformationList, new Comparator<PlayerHighScoreInformation>() {
            public int compare(PlayerHighScoreInformation object1, PlayerHighScoreInformation object2) {
                return Double.compare(object1.getPlayerScore(), object2.getPlayerScore());
            }
        });
        return playerHighScoreInformationList;
    }

    public ArrayList<PlayerHighScoreInformation> sortByInitials(ArrayList<PlayerHighScoreInformation> playerHighScoreInformationList) {
        Collections.sort(playerHighScoreInformationList, PlayerHighScoreInformation.playerInitialsComporator);
        return playerHighScoreInformationList;
    }

    public ArrayList<PlayerHighScoreInformation> sortByTime(ArrayList<PlayerHighScoreInformation> playerHighScoreInformationList) {
        Collections.sort(playerHighScoreInformationList, new Comparator<PlayerHighScoreInformation>() {
            public int compare(PlayerHighScoreInformation object1, PlayerHighScoreInformation object2) {
                return Long.compare(object1.getPlayerTime(), object2.getPlayerTime());
            }
        });
        return playerHighScoreInformationList;
    }
}
