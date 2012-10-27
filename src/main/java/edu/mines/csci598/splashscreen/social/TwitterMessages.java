package edu.mines.csci598.splashscreen.social;
import net.unto.twitter.Api;
import net.unto.twitter.TwitterProtos;

public class TwitterMessages implements SocialMessages {

    @Override
    public String retrieveLatestMessage() {
        return null;
    }

    public void retrieveAllMessages() {
        Api api = Api.builder().build();

        for (TwitterProtos.Status status : api.publicTimeline().build().get()) {
            System.out.println(String.format("%s wrote '%s'", status.getUser().getName(), status.getText()));
        }
    }
}
