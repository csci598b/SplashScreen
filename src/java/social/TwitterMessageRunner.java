package social;

public class TwitterMessageRunner {

    public static void main(String args[]) {
        TwitterMessages messenger = new TwitterMessages();
        messenger.retrieveAllMessages();
    }
}
