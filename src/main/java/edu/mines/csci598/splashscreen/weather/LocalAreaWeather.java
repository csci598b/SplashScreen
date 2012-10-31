package edu.mines.csci598.splashscreen.weather;

import org.jdom.Element;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

    public static WeatherInformation retrieveWeatherInformation() {

        try {
            URL weatherUrl = new URL("http://free.worldweatheronline.com/feed/weather.ashx?key="+API_KEY+"&q="+LOCAL_ZIP+"&num_of_days=1&format=xml");
            URLConnection weatherConnection = weatherUrl.openConnection();
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.parse(weatherConnection.getInputStream());
            doc.getDocumentElement().normalize();
            System.out.println(doc);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch (SAXException spe) {
            spe.printStackTrace();
        }

        return null;
    }
}
