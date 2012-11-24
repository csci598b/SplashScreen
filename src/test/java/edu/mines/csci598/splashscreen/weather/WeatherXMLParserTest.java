package edu.mines.csci598.splashscreen.weather;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/31/12
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeatherXMLParserTest {
    InputStream _weatherStream;
    InputStream _badWeatherStream;

    @Before
    public void setup() throws IOException {
        _weatherStream = getClass().getResourceAsStream("weather.xml");
        _badWeatherStream = getClass().getResourceAsStream("weather-missing.xml");
    }

    @Test
    public void testParseXMLFile() throws IOException, SAXException, ParserConfigurationException {
        WeatherInformation information = LocalAreaWeather.parseWeatherXML(_weatherStream);
        assertEquals(11, information.getTemperature());
        assertEquals(0.0, information.getPrecipitation());
        assertEquals(47, information.getHumidity());
        assertEquals(360, information.getWindDegree());
        assertEquals(9, information.getWindSpeed());
        assertEquals(10, information.getVisibility());
        assertEquals(1019, information.getPressure());
        assertEquals(0, information.getCloudCover());
    }

    @Test
    public void testParseIncompleteXMLFile() throws IOException, SAXException, ParserConfigurationException {
        WeatherInformation information = LocalAreaWeather.parseWeatherXML(_badWeatherStream);
        assertEquals(0.0, information.getPrecipitation());
        assertEquals(47, information.getHumidity());
        assertEquals(360, information.getWindDegree());
        assertEquals(9, information.getWindSpeed());
        assertEquals(10, information.getVisibility());
        assertEquals(1019, information.getPressure());
        assertEquals(0, information.getCloudCover());
    }
}
