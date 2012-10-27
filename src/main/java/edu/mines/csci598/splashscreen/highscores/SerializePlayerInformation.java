package edu.mines.csci598.splashscreen.highscores;

import java.io.*;
import java.util.ArrayList;

//Andrew Suter-Morris
public class SerializePlayerInformation {

    public static ArrayList<PlayerHighScoreInformation> retrievePlayerHighScoreInformation(String fileName) {
        Object deserializedHighScores = null;
        try {
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(fileName));
            deserializedHighScores = oin.readObject();
            oin.close();
        }
        catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return (ArrayList<PlayerHighScoreInformation>) deserializedHighScores;
    }

    public static void storePlayerHighScoreInformation(ArrayList<PlayerHighScoreInformation> highScores, String fileName) {
        try {
            ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fileName));
            oout.writeObject(highScores);
            oout.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
