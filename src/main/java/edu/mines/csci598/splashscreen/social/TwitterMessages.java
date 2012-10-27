package edu.mines.csci598.splashscreen.social;
import twitter4j.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterMessages implements SocialMessages {
    private final Logger _log = Logger.getLogger(TwitterMessages.class.getName());
    private Twitter _twitter;

    TwitterMessages() {
        _twitter = new TwitterFactory().getInstance();
    }

    @Override
    public String retrieveLatestMessage() {
        return null;
    }

    public ArrayList<String> retrieveAllMessages() {
        ArrayList<String> messageList = new ArrayList<String>();

        try {
            if (!authorizeAccess())
                return messageList;

            ResponseList<Status> twitterStatuses = _twitter.getMentions();
            for (Status status : twitterStatuses) {
                messageList.add("@"+ status.getUser().getScreenName() + " - " + status.getText());
            }
        }
        catch (TwitterException te) {
            te.printStackTrace();
            _log.severe("Failed to get timeline: " + te.getMessage());
        }

        return messageList;
    }

    private boolean authorizeAccess() throws TwitterException {
        try {
            RequestToken requestToken = _twitter.getOAuthRequestToken();
            AccessToken accessToken = null;
            while (null == accessToken) {
                _log.info("Open the following URL and grant access to your account:");
                _log.info(requestToken.getAuthorizationURL());
                try {
                    accessToken = _twitter.getOAuthAccessToken(requestToken);
                } catch (TwitterException te) {
                    if (401 == te.getStatusCode()) {
                        _log.severe("Unable to get the access token.");
                    } else {
                        te.printStackTrace();
                    }
                }
            }
            _log.info("Got access token.");
            _log.info("Access token: " + accessToken.getToken());
            _log.info("Access token secret: " + accessToken.getTokenSecret());
        } catch (IllegalStateException ie) {
            if (!_twitter.getAuthorization().isEnabled()) {
                _log.severe("OAuth consumer key/secret is not set.");
                return false;
            }
        }
        return true;
    }
}