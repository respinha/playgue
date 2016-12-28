package region;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by espinha on 11/21/16.
 */
public class Continent extends Region {

    private ConcurrentSkipListSet<Country> mutableCountries;
    private Map<Country, List<String>> countries;  // country, country's neighbour

    private int developmentDegree;


    public Continent(String countriesFile) throws IOException {

        countries = new HashMap<>();
        mutableCountries = new ConcurrentSkipListSet<>();
        
        /**
         * File format:
         *  Country; development degree
         */

        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(countriesFile);

            Element root = document.getDocumentElement();

            this.name = root.getElementsByTagName("Name").item(0).getTextContent();
            NodeList countriesList = root.getElementsByTagName("Country");

            for(int i = 0; i < countriesList.getLength(); i++) {
                String countryName  = getXMLStringValue(countriesList, i, "Name");
                int welfare = Integer.parseInt(getXMLStringValue(countriesList, i, "Welfare"));
                String capital = getXMLStringValue(countriesList, i, "Capital");

                CountryLocation location = new CountryLocation(getXMLStringValue(countriesList, i, "Location"));

                Country country = new Country(countryName, capital, location);
                setNeighbours(countriesList, i, country);

                MutableCountry mutableCountry = new MutableCountry(country, welfare, )
            }

            //setRegionSpec();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void setNeighbours(NodeList countriesList, int i, Country country) {

        NodeList neighbors = ((Element) countriesList.item(i)).getElementsByTagName("Neighbours");

        List<String> neighboursList;

        if(countries.containsKey(country)) {
            neighboursList = countries.get(country);

        } else {
            neighboursList = new LinkedList<>();
        }


        for(int n = 0; n < neighbors.getLength(); n++) {

            neighboursList.add(neighbors.item(i).getTextContent());
        }

        countries.put(country, neighboursList);
    }

    private String getXMLStringValue(NodeList list, int i, String tag) {
        return ((Element) list.item(i)).getElementsByTagName(tag).item(0).getTextContent();
    }
    public ConcurrentSkipListSet<Country> getCountries() {
        return countries;
    }

    public void setCountries(ConcurrentSkipListSet<Country> countries) {
        this.countries = countries;
    }

    public int getDevelopmentDegree() {
        return developmentDegree;
    }

    public void setDevelopmentDegree(int developmentDegree) {
        this.developmentDegree = developmentDegree;
    }
}
