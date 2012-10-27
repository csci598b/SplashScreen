package edu.mines.csci598.splashscreen.social;

//Andrew Suter-Morris
public class TwitterMessageRunner {

    public static void main(String args[]) {
        TwitterMessages messenger = new TwitterMessages();
        System.out.println(messenger.retrieveAllMessages());
        System.out.println(messenger.retrieveLatestMessage());
    }
}
