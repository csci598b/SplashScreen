package edu.mines.csci598.splashscreen;

import edu.mines.csci598.splashscreen.social.TwitterMessages;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/30/12
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestApp {
    public static void main(String args[]) {
        TwitterMessages messenger = new TwitterMessages();
        System.out.println(messenger.retrieveAllMessages());
        System.out.println(messenger.retrieveLatestMessage());
    }
}
