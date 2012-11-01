package edu.mines.csci598.splashscreen;

import edu.mines.csci598.splashscreen.social.TwitterMessages;
import edu.mines.csci598.splashscreen.weather.LocalAreaWeather;
import edu.mines.csci598.splashscreen.weather.WeatherInformation;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/30/12
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestApp extends JFrame {

    public TestApp() {
        initialize();
    }

    private void initialize() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JLabel label = new JLabel(getTwitterMessage());
        panel.add(label);
        label = new JLabel();
        label.setText(convertToMultiline(getWeatherInformation().toString()));
        panel.add(label);
        setTitle("Test App");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestApp app = new TestApp();
                app.setVisible(true);
            }
        });
    }

    private String getTwitterMessage() {
        TwitterMessages messages = new TwitterMessages();
        return messages.retrieveLatestMessage();
    }

    private WeatherInformation getWeatherInformation() {
        return LocalAreaWeather.retrieveWeatherInformation();
    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}
