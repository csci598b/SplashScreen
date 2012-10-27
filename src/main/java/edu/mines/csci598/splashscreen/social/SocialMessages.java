package edu.mines.csci598.splashscreen.social;

import java.util.ArrayList;

public interface SocialMessages {
    String retrieveLatestMessage();
    ArrayList<String> retrieveAllMessages();
}
