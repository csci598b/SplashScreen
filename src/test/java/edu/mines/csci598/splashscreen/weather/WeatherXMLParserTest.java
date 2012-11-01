package edu.mines.csci598.splashscreen.weather;

import edu.mines.csci598.splashscreen.highscores.PlayerHighScoreInformation;
import edu.mines.csci598.splashscreen.highscores.SerializePlayerInformation;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/31/12
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeatherXMLParserTest {
    private ArrayList<PlayerHighScoreInformation> _scores;
    private ImageIcon _playerImage;
    private long _playerTime;
    private double _playerScore;
    private String _playerInitials;

    String windDegree;
    String windSpeed;
    String cloudCover;
    String precipitation;
    String temperature;
    String pressure;
    String humidity;
    String visibility;
    InputStream weatherStream;
    URL weatherUrl;
    URLConnection weatherConnection;

    @Before
    public void setup() throws IOException {
        weatherStream = getClass().getResourceAsStream("weather.xml");
    }

    @Test
    public void testParseXMLFile() throws IOException, SAXException, ParserConfigurationException {
        WeatherInformation information = LocalAreaWeather.parseWeatherXML(weatherStream);
    }
}
