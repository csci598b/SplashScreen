package edu.mines.csci598.splashscreen.weather;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
            InputStream weatherStream = weatherConnection.getInputStream();
            Document doc = parseWeatherXML(weatherStream);
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

    public static Document parseWeatherXML(InputStream weatherStream) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = docBuilder.parse(weatherStream);
        doc.getDocumentElement().normalize();
        NodeList conditions = doc.getElementsByTagName("current_condition");


        String windDegree;
        String windSpeed;
        String cloudCover;
        String precipitation;
        String temperature;
        String pressure;
        String humidity;
        String visibility;

        for (int node = 0; node < conditions.getLength(); node++) {
            Node condition = conditions.item(node);

            if (condition.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)condition;

                System.out.println("C_TEMP: " + getTagValue("temp_C", element));
            }
        }
        return doc;
    }

    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }
}
