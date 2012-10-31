package edu.mines.csci598.splashscreen.weather;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 10/30/12
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocalAreaWeather {
    private static final String API_KEY = "b665b08214224103123010";
    private static final String LOCAL_ZIP = "80401";
    //http://www.worldweatheronline.com/weather-api.aspx#

    public static WeatherInformation retrieveWeatherInformation() {

        try {
            URL weatherUrl = new URL("http://free.worldweatheronline.com/feed/weather.ashx?key="+API_KEY+"&q="+LOCAL_ZIP+"&num_of_days=1&format=xml");

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
